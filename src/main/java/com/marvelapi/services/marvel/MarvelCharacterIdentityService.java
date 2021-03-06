package com.marvelapi.services.marvel;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.services.marvel.interfaces.CharacterIdentity;
import com.marvelapi.services.marvel.interfaces.CharacterPowerIdentity;
import com.marvelapi.web.exceptions.CharactersNotFoundException;
import com.marvelapi.web.model.CharacterPower;
import com.marvelapi.web.model.Thumbnail;
import com.swagger.marvelapi.services.marvel.model.Character;
import com.swagger.marvelapi.services.marvel.model.CharacterDataWrapper;
import com.swagger.marvelapi.services.marvel.model.Image;

/**
 * CharacterIdentity implementation for Marvel API
 *
 */
@Service
public class MarvelCharacterIdentityService implements CharacterIdentity {

    private static final Logger logger = LoggerFactory.getLogger(MarvelCharacterIdentityService.class);

    @Autowired
    private ExecutorService executor;

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Autowired
    private CharacterPowerIdentity characterPowerIdentity;

    private RestTemplate restTemplate;

    private Map<Integer, Character> characters;

    private Map<Integer, String> charactersPowers;

    private IntConsumer ic = (offset) -> {
        executor.submit(() -> {
            getCharactersAndCollect(offset);

        });
    };

    private Function<Character, com.marvelapi.web.model.Character> externalCharacterToMyCharacter = new Function<Character, com.marvelapi.web.model.Character>() {

        @Override
        public com.marvelapi.web.model.Character apply(Character character) {

            Image image = character.getThumbnail();
            Thumbnail thumbnail = new Thumbnail();
            if (Objects.nonNull(thumbnail)) {
                thumbnail.path(image.getPath()).extension(image.getExtension());
            }
            return new com.marvelapi.web.model.Character(character.getId(), character.getName(), character.getDescription(), thumbnail,
                    character.getComics().getAvailable());

        }
    };

    private BiFunction<Character, String, CharacterPower> externalCharacterToMyCharacterPower = (character, power) -> {

        return new CharacterPower(character.getId(), character.getName(), character.getDescription(), power);
    };

    private Consumer<Character> myCharacterPowerConsumer = (character) -> {
        String power = this.characterPowerIdentity.getCharacterPower(character);
        this.charactersPowers.put(character.getId(), power);
    };

    @PostConstruct
    public void init(){

        Integer totalCharacters = 0;
        restTemplate = new RestTemplate();
        try {
            final CharacterDataWrapper response = getCharacters(0,
                    marvelAPIConfig.getTs(), marvelAPIConfig.getApikey(),
                    marvelAPIConfig.getHash());

            totalCharacters = response.getData().getTotal();

            characters = new ConcurrentHashMap<>(totalCharacters);
            charactersPowers = new ConcurrentHashMap<>(totalCharacters);

            int calls = totalCharacters != null ? (int) Math.round(totalCharacters / 100.00) : 0;
            logger.debug("Executing {} ", calls);
            if (Objects.nonNull(totalCharacters) && calls > 0) {
                response.getData().getResults().parallelStream().forEach(c -> characters.put(c.getId(), c));
                IntStream.iterate(marvelAPIConfig.getOffset(), i -> i +
                        marvelAPIConfig.getOffset()).limit(calls).forEach(ic);
                logger.debug("Wainting for all threads to execute ");
                executor.awaitTermination(300, TimeUnit.SECONDS);

            }
        }
        catch (InterruptedException | ResourceAccessException ex) {

            logger.error("Error initializing Marvel Character Service" +
                    ex.getMessage());

        }
        // 1011334 (3D- man)
        this.charactersPowers.put(1011334,
                "Through concentration, Hal could merge the images of his brother imprinted on his glasses and thus cause his brother Chuck to reappear as a three-dimensional man, clad in an altered version of his experimental flight suit and endowed with physical abilities roughly three times greater than those of an ordinary human. Hal would fall into a trance-like state when Chuck appeared, and Chuck could only exist in the three-dimensional world for three hours at a time, after which Hal had to revive.\nAs the 3-D Man, Chandler possessed roughly three times the physical abilities and sensory acuity of an ordinary human in peak condition and is capable of slightly superhuman strength and speed. His stamina, durability, agility and reflexes are also estimated to be superhuman, namely roughly triple that of a human in peak physical condition. He could also sense Skrulls no matter what form they took.");
        logger.debug("MarvelCharacterIdentityService finished with character" + characters.size());
        logger.debug("and charactersPowers " + charactersPowers.size());

    }

    /**
     * @param localOffset
     *            value to start from
     */
    private void getCharactersAndCollect(final int localOffset) {

        final CharacterDataWrapper localResponse = getCharacters(localOffset, marvelAPIConfig.getTs(), marvelAPIConfig.getApikey(),
                marvelAPIConfig.getHash());
        localResponse.getData().getResults().parallelStream().forEach(c -> characters.put(c.getId(), c));
        logger.debug("Finish Collecting Characters");
        // Remove support for Character's Power
        // localResponse.getData().getResults().forEach(myCharacterPowerConsumer);
        // logger.debug("Finish Collecting Characters' power");
    }

    /**
     * @return CharacterDataWrapper with the Character data
     */
    private CharacterDataWrapper getCharacters(int offset, int ts, String apiKey, String hash) {

        final String marvelCaractersLimitUrl = String.format("%s?limit=100&offset=%d&ts=%d&apikey=%s&hash=%s", marvelAPIConfig.getCharacersUrl(), offset, ts,
                apiKey, hash);

        return restTemplate.getForObject(marvelCaractersLimitUrl, CharacterDataWrapper.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.swagger.marvelapi.services.marvel.interfaces.CharacterIdentity#
     * getAllIds()
     */
    @Override
    public Integer[] getAllCharacterIds() {

        if (Objects.nonNull(this.characters)) {
            return characters.keySet().toArray(new Integer[characters.keySet().size()]);
        }
        throw new CharactersNotFoundException("Characters do not exist");

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.marvelapi.services.marvel.interfaces.CharacterIdentity#findById(int)
     */
    @Override
    public com.marvelapi.web.model.Character findById(int characterId) {

        Character character = this.characters.get(characterId);
        if (Objects.nonNull(character)) {
            return this.externalCharacterToMyCharacter.apply(character);
        }
        throw new CharactersNotFoundException("Character Id [" + characterId + "] does not exist");

    }

    /*
     * (non-Javadoc)
     *
     * @see com.marvelapi.services.marvel.interfaces.CharacterIdentity#
     * getCharacterPower(int)
     */
    @Override
    public CharacterPower getCharacterPower(int characterId) {

        String power = this.charactersPowers.get(characterId);
        Character character = this.characters.get(characterId);
        if (Objects.nonNull(power) && Objects.nonNull(character)) {
            return externalCharacterToMyCharacterPower.apply(character, power);
        }
        throw new CharactersNotFoundException("CharacterId not found");
    }

}

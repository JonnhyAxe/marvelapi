package com.marvelapi.services.marvel;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.services.marvel.interfaces.CharacterIdentity;
import com.marvelapi.web.exceptions.CharactersNotFoundException;
import com.marvelapi.web.model.Thumbnail;
import com.swagger.marvelapi.services.marvel.model.Character;
import com.swagger.marvelapi.services.marvel.model.CharacterDataWrapper;
import com.swagger.marvelapi.services.marvel.model.Image;

/**
 * CharacterIdentity implementation for Marvel API
 *
 */
@Service
public class MarvelServiceBean implements CharacterIdentity {

    @Autowired
    ExecutorService executor;

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    private RestTemplate restTemplate;

    private Map<Integer, Character> characters;

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

    @PostConstruct
    public void init(){

        Integer totalCharacters = 0;
        restTemplate = new RestTemplate();
        try {
            final CharacterDataWrapper response = getCharacters(0, marvelAPIConfig.getTs(), marvelAPIConfig.getApikey(), marvelAPIConfig.getHash());

            totalCharacters = response.getData().getTotal();

            characters = new ConcurrentHashMap<>(totalCharacters);

            int calls = totalCharacters != null ? (int) Math.round(totalCharacters / 100.00) : 0;
            if (Objects.nonNull(totalCharacters) && calls > 0) {
                response.getData().getResults().parallelStream().forEach(c -> characters.put(c.getId(), c));
                IntStream.iterate(marvelAPIConfig.getOffset(), i -> i + marvelAPIConfig.getOffset()).limit(calls).forEach(ic);
                executor.awaitTermination(90, TimeUnit.SECONDS);

            }
        }
        catch (InterruptedException | ResourceAccessException ex) { // TODO: log exception
            ex.printStackTrace();
        }
    }

    /**
     * @param localOffset
     *            value to start from
     */
    private void getCharactersAndCollect(final int localOffset) {

        final CharacterDataWrapper localResponse = getCharacters(localOffset, marvelAPIConfig.getTs(), marvelAPIConfig.getApikey(),
                marvelAPIConfig.getHash());
        localResponse.getData().getResults().parallelStream().forEach(c -> characters.put(c.getId(), c));
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

}

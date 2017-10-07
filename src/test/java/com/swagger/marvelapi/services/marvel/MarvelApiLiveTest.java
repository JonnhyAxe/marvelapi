package com.swagger.marvelapi.services.marvel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.swagger.marvelapi.services.marvel.model.Character;
import com.swagger.marvelapi.services.marvel.model.CharacterDataWrapper;

/**
 * Test Suite for Marvel REST Api live testing
 *
 */
public class MarvelApiLiveTest {

    private RestTemplate restTemplate;

    private static final String marvelCaractersUrl = String.format("http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s", 1,
            "746d7e48cbc8d37cf4dd63c53296c3c1", "16d47e4b2b2d98f55ee6f922f246e786");

    @Before
    public void beforeTest() {

        restTemplate = new RestTemplate();
    }

    @Test
    public void givenCaracterUrl_whenSendGetForEntity_thenStatusOk() throws IOException {

        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersUrl, CharacterDataWrapper.class);

        assertThat(response.getStatus().toLowerCase(), equalTo(HttpStatus.OK.getReasonPhrase().toLowerCase()));
        assertThat(response, notNullValue());

        java.util.List<Character> result = response.getData().getResults();
        Character character = result.get(0);

        assertThat(character, notNullValue());

    }

    @Test
    public void givenCaracterUrlBy_whenSendGetForEntity_thenStatusOk() throws IOException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        int offset = 100;
        ConcurrentHashMap<Integer, Character> hashMap = new ConcurrentHashMap<Integer, Character>();
        final String marvelCaractersLimitUrl = String.format("http://gateway.marvel.com/v1/public/characters?limit=100&offset=%d&ts=%d&apikey=%s&hash=%s", 0,
                1, "746d7e48cbc8d37cf4dd63c53296c3c1", "16d47e4b2b2d98f55ee6f922f246e786");


        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersLimitUrl, CharacterDataWrapper.class);

        Integer totalCharacters = response.getData().getTotal();
        double calls = totalCharacters != null ? Math.round(totalCharacters / 100.00) - 1 : 0;

        if (Objects.nonNull(totalCharacters)) {
            response.getData().getResults().parallelStream().forEach(c -> hashMap.put(c.getId(), c));
            for (int i = 100; i <= 100 * calls; i += 100) {
                final int localOffset = i;
                executor.submit(() -> {
                    final String localMarvelCaractersLimitUrl = String.format(
                            "http://gateway.marvel.com/v1/public/characters?limit=100&offset=%d&ts=%d&apikey=%s&hash=%s", localOffset,
                            1, "746d7e48cbc8d37cf4dd63c53296c3c1", "16d47e4b2b2d98f55ee6f922f246e786");
                    final CharacterDataWrapper localResponse = restTemplate.getForObject(localMarvelCaractersLimitUrl, CharacterDataWrapper.class);
                    System.out.println(localResponse.getData().getResults().size());
                    localResponse.getData().getResults().parallelStream().forEach(c -> hashMap.put(c.getId(), c));

                });
            }

        }
        executor.awaitTermination(60, TimeUnit.SECONDS);
        assertThat(hashMap.size(), equalTo(totalCharacters));
    }

}

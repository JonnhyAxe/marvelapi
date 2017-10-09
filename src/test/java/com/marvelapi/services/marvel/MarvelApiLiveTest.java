package com.marvelapi.services.marvel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.config.MarvelApiLiveTestConfig;
import com.marvelapi.config.MarvelExternalAPIConfig;
import com.marvelapi.services.marvel.interfaces.CharacterIdentity;
import com.swagger.marvelapi.services.marvel.model.Character;
import com.swagger.marvelapi.services.marvel.model.CharacterDataWrapper;

/**
 * Test Suite for Marvel REST Api live testing
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MarvelApiLiveTestConfig.class, MarvelExternalAPIConfig.class, MarvelAPIConfig.class }, loader = AnnotationConfigContextLoader.class)
public class MarvelApiLiveTest {

    private RestTemplate restTemplate;

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Autowired
    private CharacterIdentity characterIdentity;



    @Before
    public void beforeTest() {

        restTemplate = new RestTemplate();
    }

    @Test
    public void givenCaracterUrl_whenSendGetForEntity_thenStatusOk() throws IOException {

        final String marvelCaractersUrl = String.format("%s?ts=%d&apikey=%s&hash=%s", marvelAPIConfig.getCharacersUrl(), marvelAPIConfig.getTs(),
                marvelAPIConfig.getApikey(), marvelAPIConfig.getHash());

        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersUrl, CharacterDataWrapper.class);

        assertThat(response.getStatus().toLowerCase(), equalTo(HttpStatus.OK.getReasonPhrase().toLowerCase()));
        assertThat(response, notNullValue());

        java.util.List<Character> result = response.getData().getResults();
        Character character = result.get(0);

        assertThat(character, notNullValue());

    }

    @Test
    public void givenCaracterUrlBy_whenSendGetForEntityAndGetAllCharactersIds_thenTheTotalOfCharactersAreEqual() throws IOException, InterruptedException {

        final String marvelCaractersUrl = String.format("%s?ts=%d&apikey=%s&hash=%s", marvelAPIConfig.getCharacersUrl(), marvelAPIConfig.getTs(),
                marvelAPIConfig.getApikey(), marvelAPIConfig.getHash());

        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersUrl, CharacterDataWrapper.class);
        Integer[] characterIds = characterIdentity.getAllCharacterIds();


        assertThat(response.getStatus().toLowerCase(), equalTo(HttpStatus.OK.getReasonPhrase().toLowerCase()));
        assertThat(response, notNullValue());

        assertThat(characterIds.length, equalTo(response.getData().getTotal()));
    }

}

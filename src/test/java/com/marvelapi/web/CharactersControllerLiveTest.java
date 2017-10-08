package com.marvelapi.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.config.MarvelApiLiveTestConfig;
import com.marvelapi.services.marvel.interfaces.CharacterIdentity;
import com.marvelapi.web.controller.CharacterController;
import com.marvelapi.web.model.Character;
import com.swagger.marvelapi.services.marvel.model.CharacterDataWrapper;

/**
 * Characters Controller Live Test
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MarvelAPIConfig.class, MarvelApiLiveTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class CharactersControllerLiveTest {

    private RestTemplate restTemplate;

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Autowired
    private CharacterIdentity characterIdentity;

    @Autowired
    private CharacterController characterController;

    @Before
    public void beforeTest() {

        restTemplate = new RestTemplate();
    }

    @Test
    public void givenCaracterUrl_whenSendGetForEntity_thenStatusOkAndT() throws IOException {

        final String marvelCaractersUrl = String.format("%s?ts=%d&apikey=%s&hash=%s", marvelAPIConfig.getCharacersUrl(), marvelAPIConfig.getTs(),
                marvelAPIConfig.getApikey(), marvelAPIConfig.getHash());

        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersUrl, CharacterDataWrapper.class);
        Integer[] characterIds = characterIdentity.getAllCharacterIds();
        Integer[] controllerCharacterIds = characterController.getAllCharacterIds();

        assertThat(response.getStatus().toLowerCase(), equalTo(HttpStatus.OK.getReasonPhrase().toLowerCase()));
        assertThat(response, notNullValue());
        assertThat(characterIds.length, equalTo(response.getData().getTotal()));
        assertThat(controllerCharacterIds.length, equalTo(response.getData().getTotal()));

    }

    @Test
    public void givenCaracterUrl_whenSendGetForIDEntity_thenStatusOkAndTheCharacterIsReturned() throws IOException {

        final String marvelCaractersUrl = String.format("%s?ts=%d&apikey=%s&hash=%s", marvelAPIConfig.getCharacersUrl(), marvelAPIConfig.getTs(),
                marvelAPIConfig.getApikey(), marvelAPIConfig.getHash());

        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersUrl, CharacterDataWrapper.class);
        java.util.List<com.swagger.marvelapi.services.marvel.model.Character> result = response.getData().getResults();
        com.swagger.marvelapi.services.marvel.model.Character character = result.get(0);

        Character characterDomain = characterController.findById(character.getId());

        assertThat(response.getStatus().toLowerCase(), equalTo(HttpStatus.OK.getReasonPhrase().toLowerCase()));
        assertThat(response, notNullValue());
        assertThat(character, notNullValue());
        assertThat(characterDomain, notNullValue());

        assertThat(characterDomain.getName(), equalTo(character.getName()));
        assertThat(characterDomain.getDescription(), equalTo(character.getDescription()));

        assertThat(character.getThumbnail(), notNullValue());
        assertThat(character.getThumbnail().getPath(), notNullValue());
        assertThat(characterDomain.getThumbnail().getPath(), equalTo(character.getThumbnail().getPath()));

        assertThat(characterDomain.getPopularity(), equalTo(character.getComics().getAvailable()));
    }

    @Test
    public void givenCaracterUrl_whenSendGetForWiki_thenStatusOkAndTheCharacterPowerIsReturned() throws IOException {

        String power = null;

        final String marvelCaractersUrl = String.format("%s?ts=%d&apikey=%s&hash=%s", marvelAPIConfig.getCharacersUrl(), marvelAPIConfig.getTs(),
                marvelAPIConfig.getApikey(), marvelAPIConfig.getHash());

        final CharacterDataWrapper response = restTemplate.getForObject(marvelCaractersUrl, CharacterDataWrapper.class);
        java.util.List<com.swagger.marvelapi.services.marvel.model.Character> result = response.getData().getResults();
        com.swagger.marvelapi.services.marvel.model.Character character = result.get(0);

        final String wikiUrl = character.getUrls().get(0).getUrl();

        final ResponseEntity<String> responsehTML = new RestTemplate().exchange(wikiUrl, HttpMethod.GET, null, String.class);


        assertThat(response.getStatus().toLowerCase(), equalTo(HttpStatus.OK.getReasonPhrase().toLowerCase()));
        assertThat(response, notNullValue());
        assertThat(character, notNullValue());
        assertThat(responsehTML.getStatusCode().value(), equalTo(200));

        // strip out your required data with a regex
        Pattern pattern = Pattern.compile(".*<div id=\"char-powers-contect\">(.*?)</div>.*");
        Matcher matcher = pattern.matcher(responsehTML.getBody()); System.out.println(responsehTML.getBody());

        if (matcher.find()) {
            power = matcher.group(1);
        }

        assertThat(power, notNullValue());

    }

    // @Test()
    // public final void whenHttpsUrlIsConsumed_thenException() {
    //
    // String power = null;
    // final String urlOverHttps =
    // "http://marvel.com/universe/Parker%2C_Ben_%28Uncle_Ben%29?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1";
    // final ResponseEntity<String> response = new
    // RestTemplate().exchange(urlOverHttps, HttpMethod.GET, null,
    // String.class);
    // assertThat(response.getStatusCode().value(), equalTo(200));
    //
    //
    // // strip out your required data with a regex
    // Pattern pattern = Pattern.compile(".*<div
    // id=\"char-powers-contect\">(.*?)</div>.*");
    // Matcher matcher = pattern.matcher(response.getBody());
    // System.out.println(response.getBody());
    //
    // if (matcher.find()) {
    // power = matcher.group(1);
    // }
    //
    // assertThat(power, notNullValue());
    //
    // }

}

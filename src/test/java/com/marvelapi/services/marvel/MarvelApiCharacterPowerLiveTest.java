package com.marvelapi.services.marvel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.marvelapi.config.MarvelApiCharacterPowerLiveTestConfig;
import com.marvelapi.services.marvel.interfaces.CharacterPowerIdentity;
import com.swagger.marvelapi.services.marvel.model.Character;
import com.swagger.marvelapi.services.marvel.model.Url;

/**
 * Test Suite for Marvel Character REST Api live testing
 *
 */
@Profile("live")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MarvelApiCharacterPowerLiveTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class MarvelApiCharacterPowerLiveTest {

    @Autowired
    private CharacterPowerIdentity marvelCharacterPower;

    @Test
    public void givenBenParkerCaracter_whenSendGetForEntityPower_thenPowerisNone() throws IOException {

        // Given
        Character character = new Character();
        java.util.List<Url> urls = new ArrayList<>(1);
        Url wikiUrl = new Url();
        wikiUrl.setType("wiki");
        wikiUrl.setUrl("http://marvel.com/universe/Parker%2C_Ben_%28Uncle_Ben%29?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1file");
        urls.add(wikiUrl);
        character.setUrls(urls);
        // When
        final String response = marvelCharacterPower.getCharacterPower(character);

        //
        assertThat(response, notNullValue());
        assertThat(response, equalTo("None"));
    }

}

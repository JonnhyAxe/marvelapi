package com.marvelapi.services.marvel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.marvelapi.config.MarvelApiCharacterPowerGoogleTranslator;
import com.marvelapi.services.google.translate.interfaces.CharacterPowerTranslator;

/**
 * Test suite for PowerGoogleTranslatorLiveTest
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MarvelApiCharacterPowerGoogleTranslator.class }, loader = AnnotationConfigContextLoader.class)
public class MarvelApiCharacterPowerGoogleTranslatorLiveTest {

    @Autowired
    private CharacterPowerTranslator googleCharacterPowerTranslator;


    @Test()
    public void givenPortuguese_theCorrespondingTranslation() {

        String expectedTranslation = "Hello World";
        String targetLanguage = "en";

        String actualPower = googleCharacterPowerTranslator.translateCharacterPower("Ola Mundo", targetLanguage);

        assertThat(actualPower, notNullValue());
        assertThat(actualPower, equalTo(expectedTranslation));
    }

    @Test()
    public void givenunknownLocale_thereIsNoCorrespondingTranslation() {

        String targetLanguage = "en123";

        String actualPower = googleCharacterPowerTranslator.translateCharacterPower("Ola Mundo", targetLanguage);

        assertThat(actualPower, nullValue());
    }

}

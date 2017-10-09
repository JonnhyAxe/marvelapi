package com.marvelapi.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.marvelapi.config.MarvelAPIConfig;

/**
 * Google Translator API for Marvel API
 *
 */
@PropertySource("classpath:application.properties")
public class MarvelAPIGoogleTranslate {


    Properties properties = new Properties();

    @Before
    public void init() throws IOException {

        properties.load(MarvelAPIGoogleTranslate.class.getResourceAsStream("/application.properties"));
    }
    /**
     *
     */
    private static final String GOOGLE_API_KEY = "GOOGLE_API_KEY";

    private static final String GOOGLE_API_KEY_PROPERTY = "marvel.api.google.translator.key";
    @Autowired
    private MarvelAPIConfig marvelAPIConfig;


    @Test()
    public void givenPortuguese_theCorrespondingTranslation() {

        String expectedTranslation = "Hello World";
        String googleApiKey = properties != null && properties.get(GOOGLE_API_KEY_PROPERTY) != null ? (String) properties.get(GOOGLE_API_KEY_PROPERTY) : "";

        System.setProperty(GOOGLE_API_KEY, googleApiKey);
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // Text of an "unknown" language to detect and then translate into
        // English
        final String mysteriousText = "Ola Mundo";

        // Detect the language of the mysterious text
        Detection detection = translate.detect(mysteriousText);
        String detectedLanguage = detection.getLanguage();

        // Translate the mysterious text to English
        Translation translation = translate.translate(
                mysteriousText,
                TranslateOption.sourceLanguage(detectedLanguage),
                TranslateOption.targetLanguage("en"));

        assertThat(translation.getTranslatedText(), notNullValue());
        assertThat(translation.getTranslatedText(), equalTo(expectedTranslation));
    }

}

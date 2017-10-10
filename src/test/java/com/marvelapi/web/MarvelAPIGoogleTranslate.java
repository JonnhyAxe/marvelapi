package com.marvelapi.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
// @Profile("apis")
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
        String targetLanguage = "en";
        Translation translation = null;
        Locale enLocale = new Locale(targetLanguage);
        String googleApiKey = properties != null && properties.get(GOOGLE_API_KEY_PROPERTY) != null ? (String) properties.get(GOOGLE_API_KEY_PROPERTY) : "";

        System.setProperty(GOOGLE_API_KEY, googleApiKey);
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // Text of an "unknown" language to detect and then translate into
        // English
        final String mysteriousText = "Ola Mundo";

        // Detect the language of the mysterious text
        Detection detection = translate.detect(mysteriousText);
        String detectedLanguage = detection.getLanguage();

        if (enLocale.getISO3Language() != null && enLocale.getISO3Country() != null) {
            // Translate the mysterious text to English
            translation = translate.translate(
                    mysteriousText,
                    TranslateOption.sourceLanguage(detectedLanguage),
                    TranslateOption.targetLanguage("en"));

        }

        assertThat(translation, notNullValue());
        assertThat(translation.getTranslatedText(), notNullValue());
        assertThat(translation.getTranslatedText(), equalTo(expectedTranslation));
    }



    @Test()
    public void givenISOLanguagesCodes_theCorrespondingTranslation() {

        IsoUtil iso = new IsoUtil();

        assertThat(iso.isValid(new Locale("en")), equalTo(true));

        // Chinese (Simplified) zh-CN
        assertThat(iso.isValid(new Locale("en", "CN")), equalTo(true));
        assertThat(iso.parseLocale("en-cn"), equalTo(new Locale("en", "CN")));

        // haw (ISO-639-2)
        assertThat(iso.isValid(new Locale("haw")), equalTo(true));
    }


    final class IsoUtil {

        private final Set<String> ISO_LANGUAGES = new HashSet<String>(Arrays.asList(Locale.getISOLanguages()));

        private final Set<String> ISO_COUNTRIES = new HashSet<String>(Arrays.asList(Locale.getISOCountries()));

        private IsoUtil() {
        }

        public boolean isValidISOLanguage(String s) {

            return ISO_LANGUAGES.contains(s);
        }

        public boolean isValidISOCountry(String s) {

            return ISO_COUNTRIES.contains(s);
        }

        public Locale parseLocale(String locale) {

            String[] parts = locale.split("-");
            switch (parts.length) {
                case 3: // String language, String country, String variant
                    return new Locale(parts[0], parts[1], parts[2]);
                case 2: // String language, String country, ""
                    return new Locale(parts[0], parts[1]);
                case 1: // String language, "", ""
                    return new Locale(parts[0]);
                default:
                    throw new IllegalArgumentException("Invalid locale: " + locale);
            }
        }

        private boolean isValid(Locale locale) {

            try {
                return locale.getISO3Language() != null && locale.getISO3Country() != null;
            }
            catch (MissingResourceException e) {
                return false;
            }
        }
    }

}

package com.marvelapi.services.google.translate;

import java.util.Locale;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.services.google.translate.interfaces.CharacterPowerTranslator;

/**
 * Google Translator Implementation for Character Power Translation
 *
 */
@Service
public class CharacterPowerGoogleTranslator implements CharacterPowerTranslator {


    private static final String GOOGLE_API_KEY = "GOOGLE_API_KEY";

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Autowired
    private ISOUtils localeISOUtils;


    @PostConstruct
    public void init() {

        String googleApiKey = marvelAPIConfig.getGoogleAPIKey() != null ? marvelAPIConfig.getGoogleAPIKey() : "";
        System.setProperty(GOOGLE_API_KEY, googleApiKey);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.marvelapi.services.marvel.interfaces.CharacterPowerTranslator#
     * translateCharacterPower(java.lang.String)
     */
    @Override
    public String translateCharacterPower(String power, String locale) {


        Translation translation = null;

        if (localeISOUtils.isValid(localeISOUtils.parseLocale(locale))) {

            Locale targetISOLocale = new Locale(locale);

            Translate translate = TranslateOptions.getDefaultInstance().getService();

            // Detect the language of the mysterious text
            Detection detection = translate.detect(power);
            String detectedLanguage = detection.getLanguage();

            if (targetISOLocale.getISO3Language() != null && targetISOLocale.getISO3Country() != null) {
                // Translate the mysterious text to English
                translation = translate.translate(
                        power,
                        TranslateOption.sourceLanguage(detectedLanguage),
                        TranslateOption.targetLanguage(locale));

            }
        }
        return Objects.nonNull(translation) ? translation.getTranslatedText() : null;


    }


}

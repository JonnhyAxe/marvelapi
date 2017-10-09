package com.marvelapi.web;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * Google Translator API for Marvel API
 *
 */
public class MarvelAPIGoogleTranslate {

    public static void main(String... args) {

        // Create a service object
        //
        // If no explicit credentials or API key are set, requests are
        // authenticated using Application
        // Default Credentials if available; otherwise, using an API key from
        // the GOOGLE_API_KEY
        // environment variable

        System.setProperty("GOOGLE_API_KEY", "AIzaSyDOVJKECU1EJeJvlIVFum93bCDgy3nmPCc");
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // Text of an "unknown" language to detect and then translate into
        // English
        final String mysteriousText = "Hola Mundo";

        // Detect the language of the mysterious text
        Detection detection = translate.detect(mysteriousText);
        String detectedLanguage = detection.getLanguage();

        // Translate the mysterious text to English
        Translation translation = translate.translate(
                mysteriousText,
                TranslateOption.sourceLanguage(detectedLanguage),
                TranslateOption.targetLanguage("en"));

        System.out.println(translation.getTranslatedText());
    }

}

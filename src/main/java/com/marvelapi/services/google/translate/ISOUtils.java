package com.marvelapi.services.google.translate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * Component utils for Locale ISO Validation
 *
 */
@Component
public class ISOUtils {

    private final Set<String> ISO_LANGUAGES = new HashSet<String>(Arrays.asList(Locale.getISOLanguages()));

    private final Set<String> ISO_COUNTRIES = new HashSet<String>(Arrays.asList(Locale.getISOCountries()));

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

    public boolean isValid(Locale locale) {

        try {
            return locale.getISO3Language() != null && locale.getISO3Country() != null;
        }
        catch (MissingResourceException e) {
            return false;
        }
    }

}

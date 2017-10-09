package com.marvelapi.services.google.translate.interfaces;


/**
 * Functional interface for Character Power Translation
 *
 */
@FunctionalInterface
public interface CharacterPowerTranslator {

    String translateCharacterPower(String power, String locale);
}

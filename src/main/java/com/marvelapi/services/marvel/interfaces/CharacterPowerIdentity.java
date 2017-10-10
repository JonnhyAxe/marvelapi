package com.marvelapi.services.marvel.interfaces;

import com.swagger.marvelapi.services.marvel.model.Character;

/**
 * Interface for fetching the Character Power
 *
 */
@FunctionalInterface
public interface CharacterPowerIdentity {

    /**
     * @param Character
     *            to get the power from Wiki url
     * @return Character's power
     */
    String getCharacterPower(Character characterId);
}

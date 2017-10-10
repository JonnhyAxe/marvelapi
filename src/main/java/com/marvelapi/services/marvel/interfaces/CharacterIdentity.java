package com.marvelapi.services.marvel.interfaces;

import com.marvelapi.web.model.Character;
import com.marvelapi.web.model.CharacterPower;

/**
 *
 * Standard interface API for Character Controller
 *
 */
public interface CharacterIdentity {

    /**
     * @return Integer[] with all available IDs in the target API
     */

    Integer[] getAllCharacterIds();

    /**
     * @param characterId
     * @return Character object
     */
    Character findById(int characterId);

    /**
     * @param characterId
     * @return CharacterPower object
     */
    CharacterPower getCharacterPower(int characterId);

}

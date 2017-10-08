package com.marvelapi.services.marvel.interfaces;

import com.marvelapi.web.model.Character;

/**
 *
 * Standard interface API for all
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

}

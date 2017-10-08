package com.marvelapi.services.marvel.interfaces;

import com.marvelapi.web.model.Character;

/**
 *
 *
 */
public interface CharacterIdentity {

    Integer[] getAllCharacterIds();

    /**
     * @param characterId
     * @return
     */
    Character findById(int characterId);

}

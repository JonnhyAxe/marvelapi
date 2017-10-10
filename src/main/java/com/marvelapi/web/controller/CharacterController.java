package com.marvelapi.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvelapi.services.google.translate.interfaces.CharacterPowerTranslator;
import com.marvelapi.services.marvel.interfaces.CharacterIdentity;
import com.marvelapi.web.model.Character;
import com.marvelapi.web.model.CharacterPower;
/**
 * Character Controller Beans for web interface
 *
 */
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterIdentity characterIdentity;

    @Autowired
    private CharacterPowerTranslator characterPowerTranslator;


    @RequestMapping(method = RequestMethod.GET)
    public Integer[] getAllCharacterIds() {

        return this.characterIdentity.getAllCharacterIds();
    }

    @RequestMapping(path = "/{characterId}", method = RequestMethod.GET)
    public Character findById(@PathVariable("characterId")
    @Valid
    final int characterId) {

        return characterIdentity.findById(characterId);
    }

    @RequestMapping(path = "/{characterId}/powers", method = RequestMethod.GET)
    public CharacterPower getCharacterPower(@PathVariable("characterId")
    @Valid
    final int characterId) {

        return characterIdentity.getCharacterPower(characterId);
    }

    @RequestMapping(path = "/{characterId}/powers", method = RequestMethod.GET, params = { "language" })
    public CharacterPower getCharacterPower(@PathVariable("characterId")
    @Valid
    final int characterId, @RequestParam String language) {

        CharacterPower characterPowerTranslated = characterIdentity.getCharacterPower(characterId);
        characterPowerTranslated.setPower(characterPowerTranslator.translateCharacterPower(characterPowerTranslated.getPower(),
                language));
        return characterPowerTranslated;
    }
}

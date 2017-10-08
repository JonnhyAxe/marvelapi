package com.marvelapi.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marvelapi.services.marvel.interfaces.CharacterIdentity;
import com.marvelapi.web.model.Character;
/**
 * Character Controller Beans for web interface
 *
 */
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterIdentity characterIdentity;


    @RequestMapping(method = RequestMethod.GET)
    public Integer[] getAllCharacterIds() {

        return this.characterIdentity.getAllCharacterIds();
    }

    @RequestMapping(path = "/{characterId}", method = RequestMethod.GET)
    public Character findById(@PathVariable("name")
    @Valid
    final int characterId) {

        return characterIdentity.findById(characterId);
    }

}

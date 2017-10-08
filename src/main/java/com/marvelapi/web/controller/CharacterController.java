package com.marvelapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marvelapi.services.marvel.interfaces.CharacterIdentity;

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

}

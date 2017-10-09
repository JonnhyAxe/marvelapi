package com.marvelapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * <class description>
 *
 */
@PropertySource("classpath:application.properties")
@ComponentScan({ "com.marvelapi.services.marvel.character_power" })
public class MarvelApiCharacterPowerLiveTestConfig {

    @Bean
    public MarvelAPIConfig marvelAPIConfig() {

        return new MarvelAPIConfig();
    }

}

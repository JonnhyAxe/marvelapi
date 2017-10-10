package com.marvelapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <class description>
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({ "com.marvelapi.services.google" })
public class MarvelApiCharacterPowerGoogleTranslator {


    @Bean
    public MarvelAPIConfig marvelAPIConfig() {

        return new MarvelAPIConfig();
    }

}

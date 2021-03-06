package com.marvelapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <class description>
 *
 */
@Configuration
@ComponentScan({ "com.marvelapi.web" })
public class MarvelApiLiveTestConfig {

    @Bean
    public MarvelAPIConfig marvelAPIConfig() {

        return new MarvelAPIConfig();
    }

}

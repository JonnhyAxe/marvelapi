
package com.marvelapi.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration bean for marvel Rest API
 *
 */

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({ "com.marvelapi.services" })
public class MarvelAPIConfig {


    @Value("${marvel.api.ts}")
    private int ts;

    @Value("${marvel.api.apikey}")
    private String apikey;

    @Value("${marvel.api.hash}")
    private String hash;

    @Value("${marvel.api.offsetIncrement}")
    private int offsetIncrement;

    @Value("${marvel.api.characters.url}")
    private String characersUrl;

    @Bean
    ExecutorService getExecutor() {

        return Executors.newFixedThreadPool(4);
    }

    /**
     * @return the apikey
     */
    public String getApikey() {

        return apikey;
    }

    /**
     * @return the hash
     */
    public String getHash() {

        return hash;
    }

    /**
     * @return the offsetIncrement
     */
    public int getOffset() {

        return offsetIncrement;
    }

    /**
     * @return the characersUrl
     */
    public String getCharacersUrl() {

        return characersUrl;
    }

    /**
     * @return the ts
     */
    public int getTs() {

        return ts;
    }

}
package com.marvelapi.services.marvel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Bean to load test configuration file
 *
 */
@Component
@PropertySource("classpath:application.properties")
public class LiveClientConfig {

    @Value("${marvel.api.ts}")
    private int ts;

    @Value("${marvel.api.apikey}")
    private String apikey;

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

    @Value("${marvel.api.hash}")
    private String hash;

    @Value("${marvel.api.offsetIncrement}")
    private int offsetIncrement;

    /**
     * @return the ts
     */
    public int getTs() {

        return ts;
    }

}

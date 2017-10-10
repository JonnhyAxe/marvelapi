package com.marvelapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration bean to load service beans
 *
 */
@Configuration
@ComponentScan({ "com.marvelapi.services" })
public class MarvelExternalAPIConfig {

}

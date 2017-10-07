
package com.swagger.marvelapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Configuration bean for marvel Rest API
 *
 */

@Configuration
@ComponentScan({ "com.swagger.marvelapi.services.marvel" })
@PropertySource({ "classpath:application.properties" })
public class MarvelAPIConfig {

    @Autowired
    private Environment env;

}

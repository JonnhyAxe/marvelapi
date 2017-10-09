package com.marvelapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.config.MarvelAPIWebConfig;
import com.marvelapi.config.MarvelExternalAPIConfig;

@SpringBootApplication
@Import({
        MarvelAPIConfig.class,
        MarvelAPIWebConfig.class,
        MarvelExternalAPIConfig.class
})
public class MarvelApiApplication {

	public static void main(String[] args) {

        SpringApplication.run(MarvelApiApplication.class, args);
	}
}

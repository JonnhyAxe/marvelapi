package com.marvelapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.marvelapi.config.MarvelAPIConfig;

@SpringBootApplication

@Import({
        MarvelAPIConfig.class
})
public class MarvelApiApplication {

	public static void main(String[] args) {

        SpringApplication.run(MarvelApiApplication.class, args);
	}
}

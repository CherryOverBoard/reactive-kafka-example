package com.cherryoverboard.reactivekafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ReactorApp {

    public static void main(String[] args) {
        SpringApplication.run(ReactorApp.class, args);
    }

}

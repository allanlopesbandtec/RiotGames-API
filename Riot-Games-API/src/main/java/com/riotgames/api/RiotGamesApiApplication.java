package com.riotgames.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RiotGamesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiotGamesApiApplication.class, args);
    }

}

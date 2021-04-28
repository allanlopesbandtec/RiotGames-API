package com.riotgames.Riot.Games.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;


@SpringBootApplication
@EnableFeignClients
public class RiotGamesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiotGamesApiApplication.class, args);
	}

}

package com.riotgames.api.config;

import com.riotgames.api.controller.ChampionController;
import com.riotgames.api.model.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RiotgamesConfiguration {

    @Autowired
    ChampionController championController;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(240000);
        return new RestTemplate(factory);
    }

    @Bean
    public ResponseEntity<Object> loadFindVersion() {
        return championController.loadFindVersion();
    }

    @Bean
    public ResponseEntity<Object> loadChampionsList() {
        return championController.loadAllChampions();
    }


}

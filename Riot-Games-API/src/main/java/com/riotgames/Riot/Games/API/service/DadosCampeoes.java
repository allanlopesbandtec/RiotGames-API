package com.riotgames.Riot.Games.API.service;

import com.riotgames.Riot.Games.API.domain.TodosChamps;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "campeoesapi", url = "http://ddragon.leagueoflegends.com")
public interface DadosCampeoes {

    @GetMapping(value = "/cdn/11.9.1/data/en_US/champion.json")
    TodosChamps buscarCampeoes();
}

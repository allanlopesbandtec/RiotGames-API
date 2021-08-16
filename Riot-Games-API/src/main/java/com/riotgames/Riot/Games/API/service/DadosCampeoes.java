package com.riotgames.Riot.Games.API.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "campeoesapi", url = "http://ddragon.leagueoflegends.com")
public interface DadosCampeoes {

    @GetMapping(value = "/cdn/11.9.1/data/pt_BR/champion.json")
    String buscarCampeoes();
}

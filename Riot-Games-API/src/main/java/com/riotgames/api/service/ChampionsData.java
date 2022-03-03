package com.riotgames.api.service;


import org.springframework.web.bind.annotation.GetMapping;



public interface ChampionsData {

    @GetMapping(value = "/cdn/11.9.1/data/pt_BR/champion.json")
    String buscarCampeoes();
}

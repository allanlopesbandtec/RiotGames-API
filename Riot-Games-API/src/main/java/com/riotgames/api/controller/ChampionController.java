package com.riotgames.api.controller;

import com.riotgames.api.service.ChampionWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionController {

    @Autowired
    private ChampionWS championWS;


}

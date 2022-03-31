package com.riotgames.api.controller;

import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.service.ChampionWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("champions")
public class ChampionController {

    @Autowired
    private ChampionWS championWS;

    @GetMapping
    public ResponseEntity<Object> allChampions() {
        ResponseEntity<Object> response = null;

        try {
            response = new ResponseEntity<>(championWS.allChampionsList(), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }
}

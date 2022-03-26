package com.riotgames.api.controller;

import com.riotgames.api.model.ApiError;
import com.riotgames.api.service.ChampionWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionController {

    @Autowired
    private ChampionWS championWS;

    @GetMapping("/champions")
    public ResponseEntity<Object> allChampions() {

        ResponseEntity response;

        try {
            response = new ResponseEntity<>(championWS.allChampions(), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        } catch (Exception ex) {
            response = new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}

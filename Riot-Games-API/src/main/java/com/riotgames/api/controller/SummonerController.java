package com.riotgames.api.controller;

import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.service.SummonerWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:4200"
}, methods = {
        RequestMethod.GET,
})
@RestController
@RequestMapping(path = "/summoners")
public class SummonerController {

    @Autowired
    private SummonerWS summonerWS;

    @GetMapping("/{nick}")
    public ResponseEntity<Object> searchSummoner(@PathVariable String nick) throws ApiError {
        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(summonerWS.findSummoner(nick), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }

//
//    @GetMapping("/ultima-partida/{nick}")
//    public ResponseEntity ultimaPartida(@PathVariable String nick) {
//        return ResponseEntity.ok(invocadorService.buscarUltimaPartida(nick));
//    }
}

package com.riotgames.api.controller;

import com.google.gson.Gson;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.Summoner;
import com.riotgames.api.service.ChampionWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummonerController {

    //    @Autowired
//    RiotGamesApi riotGamesApi;
//
//    @Autowired
//    private SummonerService summonerService;

    @Autowired
    private ChampionWS championWS;

    @Autowired
    private RiotgamesClient riotgamesClient;

    @Autowired
    private Gson gson;


    @GetMapping("/summoner/{nick}")
    public ResponseEntity buscarInvocador(@PathVariable String nick) throws ApiError {
        Summoner summoner = gson.fromJson(riotgamesClient.findSummonerByNick(nick), Summoner.class);
        return ResponseEntity.ok(summoner);
    }

//
//    @GetMapping("/maestria/{nick}")
//    public ResponseEntity buscarMaestriaPorInvocador(@PathVariable String nick) {
//        //3 Primeiras maestrias ?
//
//        return ResponseEntity.ok(invocadorService.campeaoPorMaestrias(nick));
//
//        //return ResponseEntity.ok(invocadorService.campeaoPorMaestrias(nick));
//    }
//
    @GetMapping("/campeoes")
    public ResponseEntity<Object> todosCampeoes() {

        ResponseEntity response;

        try {
            response = new ResponseEntity<>(championWS.allChampions(), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, HttpStatus.OK);
        } catch (Exception ex) {
            response = new ResponseEntity<>(ex, HttpStatus.OK);
        }

        return response;
    }
//
//    @GetMapping("/partidas/id/{nick}")
//    public ResponseEntity idPartidas(@PathVariable String nick, @RequestParam(required = false) Integer apartirDe, @RequestParam(required = false) Integer quantidade) {
//
//        return ResponseEntity.ok(invocadorService.buscarIdPartidas(nick, apartirDe, quantidade));
//    }
//
//    @GetMapping("/ultima-partida/{nick}")
//    public ResponseEntity ultimaPartida(@PathVariable String nick) {
//        return ResponseEntity.ok(invocadorService.buscarUltimaPartida(nick));
//    }
}

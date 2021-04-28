package com.riotgames.Riot.Games.API.controllers;
import com.riotgames.Riot.Games.API.Dto.CampeaoMaestriaDto;
import com.riotgames.Riot.Games.API.domain.Invocador;
import com.riotgames.Riot.Games.API.service.DadosCampeoes;
import com.riotgames.Riot.Games.API.service.InvocadorService;
import com.riotgames.Riot.Games.API.service.RiotGamesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/lol")
public class InvocadorController {

    @Autowired
    RiotGamesApi riotGamesApi;

    @Autowired
    InvocadorService invocadorService;

    @Autowired
    DadosCampeoes dadosCampeoes;


    @GetMapping("/invocador/{nick}")
    public ResponseEntity buscarInvocador(@PathVariable String nick){
        Invocador invocador = riotGamesApi.getInvocador(nick);
        return ResponseEntity.ok(invocador);
    }


    @GetMapping("/maestria/{nick}")
    public ResponseEntity buscarMaestriaPorInvocador(@PathVariable String nick){
        //3 Primeiras maestrias
        List<CampeaoMaestriaDto> campeoesPorMaestria = invocadorService.campeaoPorMaestrias(nick);

        return ResponseEntity.ok(campeoesPorMaestria);
    }


    @GetMapping("/campeoes")
    public ResponseEntity todosCampeoes(){
        return ResponseEntity.ok(dadosCampeoes.buscarCampeoes());
    }
}

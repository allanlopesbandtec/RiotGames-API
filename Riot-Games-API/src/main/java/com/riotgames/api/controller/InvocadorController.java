package com.riotgames.api.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvocadorController {

//    @Autowired
//    RiotGamesApi riotGamesApi;
//
//    @Autowired
//    InvocadorService invocadorService;
//
//    @Autowired
//    DadosCampeoes dadosCampeoes;


//    @GetMapping("/invocador/{nick}")
//    public ResponseEntity buscarInvocador(@PathVariable String nick) {
//        Invocador invocador = riotGamesApi.getInvocador(nick);
//        return ResponseEntity.ok(invocador);
//    }
//
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
//    @GetMapping("/campeoes")
//    public ResponseEntity todosCampeoes() {
//        return ResponseEntity.ok(invocadorService.todosCampeoes());
//    }
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

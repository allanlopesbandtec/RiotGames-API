//package com.riotgames.api.service;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
////@FeignClient(name = "riotgamesamerica", url = "https://americas.api.riotgames.com")
//public interface RiotGamesAmericas {
//
//        //Endpoint localiza todos IDs de partidas em um List<String>
//        @GetMapping(value = "/lol/match/v5/matches/by-puuid/{puuid}/ids",
//                headers = {Key.key})
//        List<String> buscarIdPartidas(@PathVariable String puuid,
//                                      @RequestParam(required = false) Integer start,
//                                      @RequestParam(required = false) Integer count);
//
//        @GetMapping(value = "lol/match/v5/matches/{partidaId}",
//        headers = {Key.key})
//        String buscarUltimaPartida(@PathVariable String partidaId);
//
//}

//package com.riotgames.api.service;
//
//import com.riotgames.api.model.Dto.ChampionByMastery;
//import com.riotgames.api.model.Summoner;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//
//public interface RiotGamesApi {
//
//    @GetMapping(value = "/lol/summoner/v4/summoners/by-name/{nick}",
//            headers = {Key.key})
//    Summoner getInvocador(@PathVariable String nick);
//
//    @GetMapping(value = "/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}",
//            headers = {Key.key})
//    List<ChampionByMastery> getCampeoesMaestriaPorInvocador(@PathVariable String encryptedSummonerId);
//
//    //Endpoint localiza todos IDs de partidas em um List<String>
//    @GetMapping(value = "/lol/match/v5/matches/by-puuid/{puuid}/ids",
//            headers = {Key.key})
//    List<String> buscarIdPartidas(@PathVariable String puuid,
//                                  @RequestParam(required = false) Integer start,
//                                  @RequestParam(required = false) Integer count);
//
//
//}

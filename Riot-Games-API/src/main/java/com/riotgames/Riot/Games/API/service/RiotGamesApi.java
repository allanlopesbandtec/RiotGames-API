package com.riotgames.Riot.Games.API.service;
import com.riotgames.Riot.Games.API.domain.Dto.CampeaoPorMaestria;
import com.riotgames.Riot.Games.API.domain.Invocador;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "riotgamesapi", url = "https://br1.api.riotgames.com")
public interface RiotGamesApi {

    @GetMapping(value = "/lol/summoner/v4/summoners/by-name/{nick}",
            headers = {Key.key})
    Invocador getInvocador(@PathVariable String nick);

    @GetMapping(value = "/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}",
            headers = {Key.key})
    List<CampeaoPorMaestria> getCampeoesMaestriaPorInvocador(@PathVariable String encryptedSummonerId);

    //Endpoint localiza todos IDs de partidas em um List<String>
    @GetMapping(value = "/lol/match/v5/matches/by-puuid/{puuid}/ids",
            headers = {Key.key})
    List<String> buscarIdPartidas(@PathVariable String puuid,
                                  @RequestParam(required = false) Integer start,
                                  @RequestParam(required = false) Integer count);


}

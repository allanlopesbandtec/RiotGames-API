package com.riotgames.Riot.Games.API.service;
import com.riotgames.Riot.Games.API.domain.CampeaoPorMaestria;
import com.riotgames.Riot.Games.API.domain.Invocador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "riotgamesapi", url = "https://br1.api.riotgames.com")
public interface RiotGamesApi {

    @GetMapping(value = "/lol/summoner/v4/summoners/by-name/{nick}",
            headers = {"X-Riot-Token=RGAPI-2398c1cd-64af-4462-9793-39581b755a88"})
    Invocador getInvocador(@PathVariable String nick);

    @GetMapping(value = "/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}",
            headers = {"X-Riot-Token=RGAPI-2398c1cd-64af-4462-9793-39581b755a88"})
    List<CampeaoPorMaestria> getCampeoesMaestriaPorInvocador(@PathVariable String encryptedSummonerId);
}

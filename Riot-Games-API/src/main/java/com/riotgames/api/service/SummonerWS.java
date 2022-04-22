package com.riotgames.api.service;

import com.google.gson.Gson;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.Summoner;
import com.riotgames.api.model.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SummonerWS {

    @Autowired
    private RiotgamesClient riotgamesClient;

    @Autowired
    private Gson gson;

    public Summoner findSummoner(String nick) throws ApiError {
        Summoner summoner = null;

        if (nick == null || nick.isBlank() || nick.isEmpty()) {
            throw new ApiError(ChampionWS.class, "findSummoner", "Nickname is empty or blank", HttpStatus.BAD_REQUEST);
        }

        try {
            summoner = gson.fromJson(riotgamesClient.findSummonerByNick(nick).getBody(), Summoner.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(SummonerWS.class, "findSummoner", "Erro ao buscar invocador", ex.getLocalizedMessage());
        }

        return summoner;
    }

}

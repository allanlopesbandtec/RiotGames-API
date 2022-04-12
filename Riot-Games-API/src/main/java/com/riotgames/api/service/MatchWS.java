package com.riotgames.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.Summoner;
import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.model.match.MatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchWS {

    @Autowired
    private RiotgamesClient riotgamesClient;

    @Autowired
    private SummonerService summonerService;

    @Autowired
    private ChampionWS championWS;

    @Autowired
    private ObjectMapper objectMapper;

    public List<String> buscarIdPartidas(String nick, Integer quantidade) throws ApiError {
        String[] matchList;
        String request;

        Summoner summoner = summonerService.findSummoner(nick);
        MatchRequest matchRequest = new MatchRequest(null, null, null, null, null, quantidade);

        try {
            request = riotgamesClient.findMatchList(summoner.getPuuId(), matchRequest);
            matchList = objectMapper.readValue(request, String[].class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "championMasteryBySummoner", "Error to list championsByMastery", ex.getLocalizedMessage());
        }

        return new ArrayList<>(Arrays.asList(matchList));
    }
}

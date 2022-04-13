package com.riotgames.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.model.match.MatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RiotgamesClient {

    @Autowired
    private RESTClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    public String findChampions() throws ApiError {
        ResponseEntity<String> result;

        try {
            //Patch -> enumeração 12.5.1
            //Linguagem de retorno pt_BR
            result = restClient.sendReceive(null, "/cdn/12.5.1/data/pt_BR/champion.json", RequestApiEnum.CHAMPION, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "findChampions", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result.getBody();
    }

    public ResponseEntity<String> findSummonerByNick(String nickName) throws ApiError {
        ResponseEntity<String> result;

        String uri = "/lol/summoner/v4/summoners/by-name/" + nickName;

        try {
            result = restClient.sendReceive(null, uri, RequestApiEnum.BR, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "findSummonerByNick", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result;
    }

    public String getChampionsByMastery(String encryptedId) throws ApiError {
        ResponseEntity<String> result;

        String uri = "/lol/champion-mastery/v4/champion-masteries/by-summoner/" + encryptedId;

        try {
            result = restClient.sendReceive(null, uri, RequestApiEnum.BR, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "getChampionsByMastery", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result.getBody();
    }

    public String findMatchList(String puuId, MatchRequest matchRequest) throws ApiError {
        ResponseEntity<String> result;

        Map<String, String> uriComponents = objectMapper.convertValue(matchRequest, Map.class);

        String uri = String.format("/lol/match/v5/matches/by-puuid/%s/ids", puuId);

        try {
            result = restClient.sendReceive(uriComponents, uri, RequestApiEnum.AMERICAS, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "getChampionsByMastery", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result.getBody();
    }

    public String findLastMatch(String matchId) throws ApiError {
        ResponseEntity<String> result;

        String uri = "/lol/match/v5/matches/" + matchId;

        try {
            result = restClient.sendReceive(null, uri, RequestApiEnum.AMERICAS, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "getChampionsByMastery", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result.getBody();
    }
}

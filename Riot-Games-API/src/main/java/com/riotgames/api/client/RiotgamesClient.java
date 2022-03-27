package com.riotgames.api.client;

import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RiotgamesClient {

    @Autowired
    private RESTClient restClient;

    public String findChampions() throws ApiError {
        ResponseEntity<String> result;

        try {
            //Patch -> enumeração 12.5.1
            //Linguagem de retorno pt_BR
            result = restClient.sendReceive("/cdn/12.5.1/data/pt_BR/champion.json", RequestApiEnum.CHAMPION, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "findChampions", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result.getBody();
    }

    public String findSummonerByNick(String nickName) throws ApiError {

        ResponseEntity<String> result;
        String uri = "/lol/summoner/v4/summoners/by-name/" + nickName;

        try {
            result = restClient.sendReceive(uri, RequestApiEnum.BR, ResponseEntity.class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "", "Falha ao efetuar request", ex.getLocalizedMessage());
        }

        return result.getBody();
    }

}

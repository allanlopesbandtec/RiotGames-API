package com.riotgames.api.client;

import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class RiotgamesClient {

    @Autowired
    private RESTClient restClient;


    public String buscarCampeoes() throws ApiError {
        String result = "";

        try {
            result = restClient.sendReceive("/cdn/11.9.1/data/pt_BR/champion.json", RequestApiEnum.CHAMPION);
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "Falha ao efetuar request");
        }

        return result;
    }

}

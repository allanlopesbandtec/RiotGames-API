package com.riotgames.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riotgames.api.client.RESTClient;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.champion.Champion;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import com.riotgames.api.model.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StaticWS {

    public static String version;

    public static List<Champion> championsList;

    private static ChampionWS championWS;

    private static RESTClient restClient;

    private static ObjectMapper objectMapper;

    public static void findChampionsList() throws ApiError {
        //Método que vai retirar do map e jogar dentro de um List
        //Por quê ? Porque é mais fácil de trabalhar com List
        List<Champion> allChampionsList;

        try {
            //Obtendo valor dos campeões
            Map<String, Champion> championMap = championWS.mapChampions();

            //Validação com base no championMap
            if (!championMap.isEmpty()) {
                //Conversão dos valores do map para ArrayList
                allChampionsList = new ArrayList<>(championMap.values());
            } else {
                //Caso a lista esteja vazia não há muito o que ser feito, pode ser um erro no servidor...
                throw new ApiError(ChampionWS.class, "allChampionsList", "Map of champions isEmpty", HttpStatus.NO_CONTENT);
            }
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "allChampionsList", "Error to List the mapped champions", ex.getLocalizedMessage());
        }

        championsList = allChampionsList;
    }

    public static void findPatch() throws ApiError {
        ResponseEntity<String> result;
        String[] arrayVersion;

        try {
            result = restClient.sendReceive(null, "api/versions.json", RequestApiEnum.DDRAGON);
            arrayVersion = objectMapper.readValue(result.getBody(), String[].class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "findPatch", "Error to set a last version", ex.getLocalizedMessage());
        }

        version = arrayVersion[0];
    }

    @Autowired
    public void setRestClient(RESTClient restClient) {
        StaticWS.restClient = restClient;
    }

    @Autowired
    public void setChampionWS(ChampionWS championWS) {
        StaticWS.championWS = championWS;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        StaticWS.objectMapper = objectMapper;
    }
}

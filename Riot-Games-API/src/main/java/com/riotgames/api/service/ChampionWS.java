package com.riotgames.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.Dto.ChampionDto;
import com.riotgames.api.model.Dto.ChampionMasteryDto;
import com.riotgames.api.model.Summoner;
import com.riotgames.api.model.champion.Champion;
import com.riotgames.api.model.champion.ChampionByMastery;
import com.riotgames.api.model.champion.ChampionDetail;
import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.utils.UtilsWS;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChampionWS {

    @Autowired
    private RiotgamesClient riotgamesClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SummonerWS summonerWS;

    protected Map<String, Champion> mapChampions() throws ApiError {
        Map<String, Champion> mapChampions = new HashMap<>();
        String request = "";

        try {
            /** @apiNote Requisição com campeões da página web ->
             *                          {@link {http://ddragon.leagueoflegends.com/cdn/12.5.1/data/pt_BR/champion.json}}
             */
            request = riotgamesClient.findChampions();
            //Mapeamento da resposta, dentro da chave "data" com o JSONObject vamos encontrar os campeões
            String campeoes = new JSONObject(request).getJSONObject("data").toString();
            //ObjectMapper + contrutor customizado vai retornar campões mapeados!
            mapChampions = objectMapper.readValue(campeoes, Champion.class).getchampionMap();
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "mapChampions", "Error to map champions", ex.getLocalizedMessage());
        }

        return mapChampions;
    }

    public ChampionDetail getChampDetail(String championName) throws ApiError {
        ChampionDetail championDetail = null;
        String request;


        if (championName == null || championName.isBlank() || championName.isEmpty()) {
            throw new ApiError(ChampionWS.class, "getChampDetail", "Champion name is empty or blank", HttpStatus.BAD_REQUEST);
        }

        try {
            request = riotgamesClient.findChampion(championName);
            String championResponse = new JSONObject(request).getJSONObject("data").getJSONObject(championName).toString();
            championDetail = objectMapper.readValue(championResponse, ChampionDetail.class);

            UtilsWS.saveRequest(championDetail.getName(), championDetail.getTitle());
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "getChampDetail", "Error to find champion", ex.getLocalizedMessage());
        }

        return championDetail;
    }

    protected List<ChampionByMastery> getMasteryBySummoner(String encryptedSummonerId) throws ApiError {
        ChampionByMastery[] championMastery;
        String request;

        try {
            request = riotgamesClient.getChampionsByMastery(encryptedSummonerId);
            championMastery = objectMapper.readValue(request, ChampionByMastery[].class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "championMasteryBySummoner", "Error to list championsByMastery", ex.getLocalizedMessage());
        }

        return new ArrayList<>(Arrays.asList(championMastery));
    }

    public List<ChampionMasteryDto> getChampionsByMastery(String nick) throws ApiError {
        //Buscando invocador
        Summoner summoner = summonerWS.findSummoner(nick);

        //Recuperando maestrias por invocador
        List<ChampionByMastery> championsByMasteries = getMasteryBySummoner(summoner.getId());

        //Todos os champs
        List<ChampionDto> championDto = getCampeaoDtos();

        //Lista que vamos retornar Api
        return getChampionMasteryDto(championsByMasteries, championDto);
    }

    public List<ChampionDto> getCampeaoDtos() throws ApiError {
        return StaticWS.championsList.stream().map(ChampionDto::new).collect(Collectors.toList());
    }

    //Match entre campeoes com e sem maestria
    public List<ChampionMasteryDto> getChampionMasteryDto(List<ChampionByMastery> masteryList, List<ChampionDto> dtoList) throws ApiError {

        List<ChampionMasteryDto> championsMasteryDtos = new ArrayList<>();
        try {
            //1 for para maestrias do jogador e segundo para Campeao
            for (ChampionByMastery championByMastery : masteryList) {
                String chmapionId = championByMastery.getChampionId().toString();

                if (!dtoList.isEmpty()) {
                    for (ChampionDto champDto : dtoList) {
                        if (champDto.getKey().equals(chmapionId)) {
                            ChampionMasteryDto championMasteryDto = new ChampionMasteryDto(championByMastery, champDto);
                            championsMasteryDtos.add(championMasteryDto);
                        }
                    }
                } else {
                    throw new ApiError(ChampionWS.class, "championByMastery", "Mastery list is empty", HttpStatus.NO_CONTENT);
                }
            }

            return championsMasteryDtos;

        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "championByMastery", "Error to merge champions with mastery ranks", ex.getLocalizedMessage());
        }
    }

    public List<ChampionMasteryDto> getFilterSearchChampions(String nick, String role) throws ApiError {
        List<ChampionMasteryDto> masteryDtos = getChampionsByMastery(nick);
        List<ChampionMasteryDto> filterMasteryList = new ArrayList<>();

        try {

            for (ChampionMasteryDto championMasteryDto : masteryDtos) {

                for (String tags : championMasteryDto.getTags()) {

                    if (!championMasteryDto.getChestWinned() && tags.equals(role)) {
                        filterMasteryList.add(championMasteryDto);
                    }
                }
            }
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "getCustomSearchChampions", "Error to filter champs by role", ex.getLocalizedMessage());
        }

        //Ajustar validação
        if (filterMasteryList.isEmpty()) {
            throw new ApiError(ChampionWS.class, "getFilterSearchChampions", "Mastery list is empty, check de lane name", HttpStatus.BAD_REQUEST);
        }

        return filterMasteryList;
    }

    public String listChampionsMostPlayed() throws ApiError {
        String request = riotgamesClient.getChampionsMostPlayed();

        try {
            ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
            se.eval(String.format("Object.bindProperties(this, %s);", request));
            se.eval("print(this.name.onClick)");
        } catch (ScriptException e) {
            e.printStackTrace();
        }


        return request;
    }
}

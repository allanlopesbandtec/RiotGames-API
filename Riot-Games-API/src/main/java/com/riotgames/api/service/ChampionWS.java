package com.riotgames.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.champion.Champion;
import com.riotgames.api.model.champion.ChampionByMastery;
import com.riotgames.api.model.Dto.ChampionDto;
import com.riotgames.api.model.Dto.ChampionMasteryDto;
import com.riotgames.api.model.Summoner;
import com.riotgames.api.model.error.ApiError;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    private Map<String, Champion> mapChampions() throws ApiError {
        Map<String, Champion> mapChampions = new HashMap<>();
        String request = "";

        try {
            //Requisição com campeões da página web -> http://ddragon.leagueoflegends.com/cdn/12.5.1/data/pt_BR/champion.json
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

    private List<ChampionByMastery> getMasteryBySummoner(String encryptedSummonerId) throws ApiError {
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

    public List<Champion> getChampionsList() throws ApiError {
        //Método que vai retirar do map e jogar dentro de um List
        //Por quê ? Porque é mais fácil de trabalhar com List
        List<Champion> allChampionsList = new ArrayList<>();

        try {
            //Obtendo valor dos campeões
            Map<String, Champion> championMap = mapChampions();

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

        return allChampionsList;
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
        return getChampionsList().stream().map(ChampionDto::new).collect(Collectors.toList());
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


    public ChampionDetailResponse getChampDetail(String championName) {


    }
}

package com.riotgames.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.Champion;
import com.riotgames.api.model.Dto.ChampionByMastery;
import com.riotgames.api.model.Dto.ChampionDto;
import com.riotgames.api.model.Dto.ChampionMasteryDto;
import com.riotgames.api.model.Summoner;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChampionWS {

    @Autowired
    private RiotgamesClient riotgamesClient;

    @Autowired
    private ObjectMapper objectMapper;

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
//
//        List<Champion> listaDeCampeoes = new ArrayList<>();
//
//        for (String cString : chave.keySet()) {
//            //cString Lê cada campeão dentro da chave "data" como String
//            //Para extrair esse campeão como um objeto devemos usar o JSONObject
//
//            try {
//                JSONObject campeaoObj = chave.getJSONObject(cString);
//                //Para cada Objeto criei uma classe e mapeei os dados
//
//                Info info = new Info(
//                        campeaoObj.getJSONObject("info").getInt("attack"),
//                        campeaoObj.getJSONObject("info").getInt("defense"),
//                        campeaoObj.getJSONObject("info").getInt("magic"),
//                        campeaoObj.getJSONObject("info").getInt("difficulty")
//                );
//
//                Image image = new Image(campeaoObj.getJSONObject("image").getString("full"),
//                        campeaoObj.getJSONObject("image").getString("sprite"),
//                        campeaoObj.getJSONObject("image").getString("group"),
//                        campeaoObj.getJSONObject("image").getInt("x"),
//                        campeaoObj.getJSONObject("image").getInt("y"),
//                        campeaoObj.getJSONObject("image").getInt("w"),
//                        campeaoObj.getJSONObject("image").getInt("h")
//                );
//
//                Stats stats = new Stats(
//                        campeaoObj.getJSONObject("stats").getInt("hp"),
//                        campeaoObj.getJSONObject("stats").getInt("hpperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("mp"),
//                        campeaoObj.getJSONObject("stats").getInt("mpperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("movespeed"),
//                        campeaoObj.getJSONObject("stats").getInt("armor"),
//                        campeaoObj.getJSONObject("stats").getDouble("armorperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("spellblock"),
//                        campeaoObj.getJSONObject("stats").getDouble("spellblockperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("attackrange"),
//                        campeaoObj.getJSONObject("stats").getInt("hpregen"),
//                        campeaoObj.getJSONObject("stats").getInt("hpregenperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("mpregen"),
//                        campeaoObj.getJSONObject("stats").getInt("mpregenperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("crit"),
//                        campeaoObj.getJSONObject("stats").getInt("critperlevel"),
//                        campeaoObj.getJSONObject("stats").getInt("attackdamage"),
//                        campeaoObj.getJSONObject("stats").getInt("attackdamageperlevel"),
//                        campeaoObj.getJSONObject("stats").getDouble("attackspeedperlevel"),
//                        campeaoObj.getJSONObject("stats").getDouble("attackspeed")
//                );
//
//
//
//                List<String> campeoesList = new ArrayList<>();
//
//                for (Object tag : campeaoObj.getJSONArray("tags")) {
//                    campeoesList.add(tag.toString());
//                }
//
//                Champion champion = new Champion(
//                        campeaoObj.getString("version"),
//                        campeaoObj.getString("id"),
//                        campeaoObj.getString("key"),
//                        campeaoObj.getString("name"),
//                        campeaoObj.getString("title"),
//                        campeaoObj.getString("blurb"),
//                        info,
//                        image,
//                        campeoesList,
//                        campeaoObj.getString("partype"),
//                        stats
//                );
//
//                listaDeCampeoes.add(champion);
//
//            } catch (NullPointerException e){
//                System.out.println("-------Erro-------\n" + e.toString());
//            }
//        }
        return mapChampions;
    }

    public List<Champion> allChampionsList() throws ApiError {
        //Método que vai retirar do map e jogar dentro de um List
        //Por quê ? Porque é mais fácil de trabalhar com List
        List<Champion> allChampionsList = new ArrayList<>();

        try {
            //Obtendo valor dos campeões
            Map<String, Champion> championMap = mapChampions();

            //Validação com base no championMap
            if (!championMap.isEmpty()) {
                //Conversão dos valores do map para ArrayList
                allChampionsList = new ArrayList<>(mapChampions().values());
            } else {
                //Caso a lista esteja vazia não há muito o que ser feito, pode ser um erro no servidor...
                throw new ApiError(ChampionWS.class, "allChampionsList", "Map of champions isEmpty", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, "allChampionsList", "Error to List the mapped champions", ex.getLocalizedMessage());
        }

        return allChampionsList;
    }

//        public List<ChampionMasteryDto> campeaoPorMaestrias(String nick) throws ApiError {
//        //Buscando invocador
//        Summoner summoner = buscaInvocador(nick);
//        //Recuperando maestrias por invocador
//        List<ChampionByMastery> championByMasteries = riotGamesApi.getCampeoesMaestriaPorInvocador(summoner.getId());
//        //Todos os champs
//        List<ChampionDto> championDto = championWS.getCampeaoDtos();
//        //Lista que vamos retornar Api (Sim vou mudar o nome da classe)
//        List<ChampionMasteryDto> championMasteryDtos = new ArrayList<>();
//        //1 for para maestrias do jogador e segundo para Campeao
//        for (ChampionByMastery cPorM : championByMasteries) {
//            String idCampeao = cPorM.getIdCampeao().toString();
//            for (ChampionDto c : championDto) {
//                if (c.getKey().equals(idCampeao)) {
//                    ChampionMasteryDto championMasteryDto = new ChampionMasteryDto(cPorM, c);
//                    championMasteryDtos.add(championMasteryDto);
//                }
//            }
//        }
//
//        return championMasteryDtos;
//    }

    public List<ChampionDto> getCampeaoDtos() throws ApiError {
        return allChampionsList().stream().map(ChampionDto::new).collect(Collectors.toList());
    }

}

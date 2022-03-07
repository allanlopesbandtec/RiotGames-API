package com.riotgames.api.service;

import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.Champion;
import com.riotgames.api.model.Dto.ChampionDto;
import com.riotgames.api.model.champion.Image;
import com.riotgames.api.model.champion.Info;
import com.riotgames.api.model.champion.Stats;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionWS {

    @Autowired
    private RiotgamesClient riotgamesClient;

    public List<Champion> allChampions() throws ApiError {
        String request = "";

        try {
             request = riotgamesClient.findChampions();
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(ChampionWS.class, ex.getLocalizedMessage());
        }



        JSONObject campeoes = new JSONObject(request);
        JSONObject chave = campeoes.getJSONObject("data");

        List<Champion> listaDeCampeoes = new ArrayList<>();

        for (String cString : chave.keySet()) {
            //cString Lê cada campeão dentro da chave "data" como String
            //Para extrair esse campeão como um objeto devemos usar o JSONObject

            try {
                JSONObject campeaoObj = chave.getJSONObject(cString);
                //Para cada Objeto criei uma classe e mapeei os dados

                Info info = new Info(
                        campeaoObj.getJSONObject("info").getInt("attack"),
                        campeaoObj.getJSONObject("info").getInt("defense"),
                        campeaoObj.getJSONObject("info").getInt("magic"),
                        campeaoObj.getJSONObject("info").getInt("difficulty")
                );

                Image image = new Image(campeaoObj.getJSONObject("image").getString("full"),
                        campeaoObj.getJSONObject("image").getString("sprite"),
                        campeaoObj.getJSONObject("image").getString("group"),
                        campeaoObj.getJSONObject("image").getInt("x"),
                        campeaoObj.getJSONObject("image").getInt("y"),
                        campeaoObj.getJSONObject("image").getInt("w"),
                        campeaoObj.getJSONObject("image").getInt("h")
                );

                Stats stats = new Stats(
                        campeaoObj.getJSONObject("stats").getInt("hp"),
                        campeaoObj.getJSONObject("stats").getInt("hpperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("mp"),
                        campeaoObj.getJSONObject("stats").getInt("mpperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("movespeed"),
                        campeaoObj.getJSONObject("stats").getInt("armor"),
                        campeaoObj.getJSONObject("stats").getDouble("armorperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("spellblock"),
                        campeaoObj.getJSONObject("stats").getDouble("spellblockperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("attackrange"),
                        campeaoObj.getJSONObject("stats").getInt("hpregen"),
                        campeaoObj.getJSONObject("stats").getInt("hpregenperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("mpregen"),
                        campeaoObj.getJSONObject("stats").getInt("mpregenperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("crit"),
                        campeaoObj.getJSONObject("stats").getInt("critperlevel"),
                        campeaoObj.getJSONObject("stats").getInt("attackdamage"),
                        campeaoObj.getJSONObject("stats").getInt("attackdamageperlevel"),
                        campeaoObj.getJSONObject("stats").getDouble("attackspeedperlevel"),
                        campeaoObj.getJSONObject("stats").getDouble("attackspeed")
                );



                List<String> campeoesList = new ArrayList<>();

                for (Object tag : campeaoObj.getJSONArray("tags")) {
                    campeoesList.add(tag.toString());
                }

                Champion champion = new Champion(
                        campeaoObj.getString("version"),
                        campeaoObj.getString("id"),
                        campeaoObj.getString("key"),
                        campeaoObj.getString("name"),
                        campeaoObj.getString("title"),
                        campeaoObj.getString("blurb"),
                        info,
                        image,
                        campeoesList,
                        campeaoObj.getString("partype"),
                        stats
                );

                listaDeCampeoes.add(champion);

            } catch (NullPointerException e){
                System.out.println("-------Erro-------\n" + e.toString());
            }
        }

        return listaDeCampeoes;
    }

    public List<ChampionDto> getCampeaoDtos() throws ApiError {
        return allChampions().stream().map(ChampionDto::new).collect(Collectors.toList());
    }

}

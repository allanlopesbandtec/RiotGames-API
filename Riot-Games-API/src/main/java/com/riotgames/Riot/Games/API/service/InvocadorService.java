package com.riotgames.Riot.Games.API.service;

import com.riotgames.Riot.Games.API.domain.Campeao;
import com.riotgames.Riot.Games.API.domain.Dto.CampeaoDto;
import com.riotgames.Riot.Games.API.domain.Dto.*;
import com.riotgames.Riot.Games.API.domain.Dto.CampeaoPorMaestria;
import com.riotgames.Riot.Games.API.domain.Invocador;
import com.riotgames.Riot.Games.API.domain.campeao.Image;
import com.riotgames.Riot.Games.API.domain.campeao.Info;
import com.riotgames.Riot.Games.API.domain.campeao.Stats;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class InvocadorService {

    @Autowired
    RiotGamesApi riotGamesApi;

    @Autowired
    DadosCampeoes dadosCampeoes;

    @Autowired
    RiotGamesAmericas riotGamesAmericas;

    public Invocador buscaInvocador(String nick) {
        return riotGamesApi.getInvocador(nick);
    }


    public List<Campeao> todosCampeoes(){

        String todos = dadosCampeoes.buscarCampeoes();
        JSONObject campeoes = new JSONObject(todos);
        JSONObject chave = campeoes.getJSONObject("data");

        List<Campeao> listaDeCampeoes = new ArrayList<>();

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

                Campeao campeao = new Campeao(
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

                listaDeCampeoes.add(campeao);

            } catch (NullPointerException e){
                System.out.println("-------Erro-------\n" + e.toString());
            }
        }

        return listaDeCampeoes;
    }

    public List<CampeaoDto> getCampeaoDtos(){
        return todosCampeoes().stream().map(CampeaoDto::new).collect(Collectors.toList());
    }

    public List<CampeaoMaestriaDto> campeaoPorMaestrias(String nick){
        //Buscando invocador
        Invocador invocador = buscaInvocador(nick);
        //Recuperando maestrias por invocador
        List<CampeaoPorMaestria> campeaoPorMaestrias = riotGamesApi.getCampeoesMaestriaPorInvocador(invocador.getId());
        //Todos os champs
        List<CampeaoDto> campeaoDto = getCampeaoDtos();
        //Lista que vamos retornar Api (Sim vou mudar o nome da classe)
        List<CampeaoMaestriaDto> campeaoMaestriaDtos = new ArrayList<>();
        //1 for para maestrias do jogador e segundo para Campeao
        for (CampeaoPorMaestria cPorM : campeaoPorMaestrias){
            String idCampeao = cPorM.getIdCampeao().toString();
            for (CampeaoDto c : campeaoDto){
                if (c.getKey().equals(idCampeao)){
                    CampeaoMaestriaDto campeaoMaestriaDto = new CampeaoMaestriaDto(cPorM, c);
                    campeaoMaestriaDtos.add(campeaoMaestriaDto);
                }
            }
        }

        return campeaoMaestriaDtos;
    }

    public List<String> buscarIdPartidas(String nick, Integer apartirDe, Integer quantidade){

        Invocador invocador = buscaInvocador(nick);

        return riotGamesAmericas.buscarIdPartidas(invocador.getPuuId(), apartirDe, quantidade);
    }

    public String buscarUltimaPartida(String nick) {

         List<String> listaPartidas = buscarIdPartidas(nick, 0,20);
         System.out.println("Última partida encontrada -" + listaPartidas.get(0));

         String todos = riotGamesAmericas.buscarUltimaPartida(listaPartidas.get(0));

         JSONObject campeoes = new JSONObject(todos);
         JSONObject chave = campeoes.getJSONObject("metadata");

        System.out.println(chave);


         return "teste";
    }
}

package com.riotgames.api.service;

import com.riotgames.api.client.RiotgamesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummonerWS {

//    @Autowired
//    private RiotGamesApi riotGamesApi;

    @Autowired
    private RiotgamesClient riotgamesClient;

    @Autowired
    private ChampionWS championWS;

//
//    public List<String> buscarIdPartidas(String nick, Integer apartirDe, Integer quantidade){
//        Summoner summoner = buscaInvocador(nick);
//        return riotGamesAmericas.buscarIdPartidas(summoner.getPuuId(), apartirDe, quantidade);
//    }
//
//    public String buscarUltimaPartida(String nick) {
//
//         List<String> listaPartidas = buscarIdPartidas(nick, 0,20);
//         System.out.println("Última partida encontrada -" + listaPartidas.get(0));
//
//         String todos = riotGamesAmericas.buscarUltimaPartida(listaPartidas.get(0));
//
//         JSONObject campeoes = new JSONObject(todos);
//         JSONObject chave = campeoes.getJSONObject("metadata");
//
//         return "teste";
//    }
}

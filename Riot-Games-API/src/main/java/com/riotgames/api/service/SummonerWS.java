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

//    public Summoner buscaInvocador(String nick) {
//        return riotgamesClient.findSummonerByNick(nick);
//    }


//    public List<ChampionMasteryDto> campeaoPorMaestrias(String nick) throws ApiError {
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

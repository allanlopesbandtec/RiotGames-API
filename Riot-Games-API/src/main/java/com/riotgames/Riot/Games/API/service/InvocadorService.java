package com.riotgames.Riot.Games.API.service;

import com.riotgames.Riot.Games.API.Dto.CampeaoMaestriaDto;
import com.riotgames.Riot.Games.API.domain.CampeaoPorMaestria;
import com.riotgames.Riot.Games.API.domain.Invocador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InvocadorService {

    @Autowired
    RiotGamesApi riotGamesApi;

    public Invocador buscaInvocador(String nick){
        return riotGamesApi.getInvocador(nick);
    }


    public List<CampeaoMaestriaDto> campeaoPorMaestrias(String nick){

        Invocador invocador = buscaInvocador(nick);

        List<CampeaoPorMaestria> campeoesMaestria = riotGamesApi.getCampeoesMaestriaPorInvocador(invocador.getId());

        List<CampeaoMaestriaDto> campeaoPorMaestriasFiltrada = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

           CampeaoPorMaestria c = campeoesMaestria.remove(i);
           campeaoPorMaestriasFiltrada.add(new CampeaoMaestriaDto(
                   c.getIdInvocador(),
                   c.getPontosComCampeao(),
                   c.getNivelCampeao()));
        }

        return campeaoPorMaestriasFiltrada;

    }


}

package com.riotgames.api.controller;

import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.service.ChampionWS;
import com.riotgames.api.service.StaticWS;
import com.riotgames.api.utils.UtilsWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("champions")
public class ChampionController {

    @Autowired
    private ChampionWS championWS;

    @Autowired
    private UtilsWS utilsWS;

    @RequestMapping(value = "findVersion", method = RequestMethod.GET)
    public ResponseEntity<Object> loadFindVersion() throws ApiError {
        ResponseEntity<Object> response = null;

        try {
            StaticWS.findPatch();
            response = new ResponseEntity<>(StaticWS.version, HttpStatus.OK);
            System.out.println("New version loaded, HttpStatus: " + HttpStatus.OK);
        } catch (ApiError ex) {
            ex.printStackTrace();
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("/load-championsList")
    public ResponseEntity<Object> loadAllChampions() {
        ResponseEntity<Object> response = null;

        try {
            StaticWS.findChampionsList();
            response = new ResponseEntity<>(StaticWS.championsList, StaticWS.championsList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
            System.out.println("Champion list filled, HttpStatus: " + HttpStatus.OK);
        } catch (ApiError ex) {
            ex.printStackTrace();
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Object> allChampions() {
        ResponseEntity<Object> response = null;

        try {

            if (StaticWS.championsList.isEmpty()) {
                StaticWS.findChampionsList();
            } else {
                response = new ResponseEntity<>(StaticWS.championsList, HttpStatus.OK);
            }

        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        } catch (Exception ex) {
            response = new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("/find-champion/{championName}")
    public ResponseEntity<Object> getOneChampion(@PathVariable String championName) {
        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(championWS.getChampDetail(championName), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;

    }

    @GetMapping("/champions/{nick}")
    public ResponseEntity<Object> allChampionsByMastery(@PathVariable String nick) {
        ResponseEntity<Object> response = null;

        try {
            response = new ResponseEntity<>(championWS.getChampionsByMastery(nick), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }

    @GetMapping("/by-lane/{nick}")
    public ResponseEntity<Object> allChampionsByMastery(@PathVariable String nick,
                                                        @RequestParam(name = "lane", required = false) String lane) {
        ResponseEntity<Object> response = null;

        try {
            response = new ResponseEntity<>(championWS.getFilterSearchChampions(nick, lane), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }

    @GetMapping("/most-played")
    public ResponseEntity<Object> mostPlayedChampions(){

        ResponseEntity<Object> response = null;

        try {
            response = new ResponseEntity<>(championWS.listChampionsMostPlayed(), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }

}

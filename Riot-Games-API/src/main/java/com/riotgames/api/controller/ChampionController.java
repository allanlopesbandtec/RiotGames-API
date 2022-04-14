package com.riotgames.api.controller;

import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.service.ChampionWS;
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
            UtilsWS.findPatch();
            response = new ResponseEntity<>("New version loaded", HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        } catch (Exception ex) {
            response = new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<Object> allChampions() {
        ResponseEntity<Object> response = null;

        try {
            response = new ResponseEntity<>(championWS.getChampionsList(), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }

    @GetMapping("/{nick}")
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
            response = new ResponseEntity<>(championWS.getCustomSearchChampions(nick, lane), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }

}

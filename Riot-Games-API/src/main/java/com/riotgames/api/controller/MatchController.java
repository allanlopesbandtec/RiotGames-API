package com.riotgames.api.controller;

import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.service.MatchWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/matchs")
public class MatchController {

    @Autowired
    private MatchWS matchWS;

    @GetMapping("/{nick}")
    public ResponseEntity<Object> searchMatchs(@PathVariable String nick,
                                               @RequestParam Integer quantidade) throws ApiError {
        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(matchWS.buscarIdPartidas(nick, quantidade), HttpStatus.OK);
        } catch (ApiError ex) {
            response = new ResponseEntity<>(ex, ex.getHttpStatus());
        }

        return response;
    }
}

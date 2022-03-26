package com.riotgames.api.client;

import com.google.gson.Gson;
import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RESTClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public ResponseEntity<String> sendReceive(String uri, RequestApiEnum requestApiEnum, Class retorno) throws ApiError {
        ResponseEntity<String> responseEntity;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());

        try {
            responseEntity = restTemplate.exchange(requestApiEnum.getClientAmbiente() + uri, HttpMethod.GET, entity, String.class);
        } catch (RestClientException ex) {

            ResponseEntity<Object> responseException;

            if (ex instanceof HttpStatusCodeException){
                HttpStatusCodeException exAux = (HttpStatusCodeException) ex;
//                Object responseError = gson.fromJson(((HttpStatusCodeException) ex).getResponseBodyAsString(), ex.getClass());
                responseException = new ResponseEntity<>(exAux.getResponseBodyAsString(), exAux.getStatusCode());
            } else {
                responseException =  new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            throw new ApiError(RESTClient.class,  "Erro no envio da requisição para RiotGames", responseException.getStatusCode(), responseException.getBody().toString());
        }

        return responseEntity;
    }

    public HttpHeaders getHeaders() throws ApiError {

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("X-Riot-Token", "RGAPI-acfd08b9-b2ba-415c-b455-86072e44a8a6");
        } catch (Exception ex) {
            throw new ApiError(RESTClient.class, "Erro ao montar requisição", ex.getLocalizedMessage());
        }

        return headers;
    }

}

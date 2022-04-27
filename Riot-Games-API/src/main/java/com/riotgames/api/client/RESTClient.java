package com.riotgames.api.client;

import com.google.gson.Gson;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.utils.UtilsWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RESTClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    //Método que vai enviar requisições para as APIs

    /**
     *
     * @param requestParams @null
     * @param uri
     * @param requestApiEnum @null
     * @param retorno @null
     * @return ResponseEntity
     * @throws ApiError
     */

    public ResponseEntity<String> sendReceive(Map<String, String> requestParams, String uri, RequestApiEnum requestApiEnum, Class retorno) throws ApiError {
        //Resposta, request, endpoint
        ResponseEntity<String> responseEntity;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        String endpoint = "";

        try {
            //Montando requisição baseada na API desejada
            //Uri passada com base no método
            //Headers para request

            endpoint = UtilsWS.buildUri(
                    requestParams,
                    requestApiEnum != null ? requestApiEnum.getClientAmbiente() : "",
                    uri);

            responseEntity = restTemplate.exchange(
                    endpoint,
                    HttpMethod.GET,
                    entity,
                    String.class);
        } catch (RestClientException ex) {
            //Criação de um responseEntity para enviar o Erro
            ResponseEntity<Object> responseException;

            if (ex instanceof HttpStatusCodeException) {
                //Caso o erro for dessa instancia vai criar o objeto tratado com status
                HttpStatusCodeException httpStatusError = (HttpStatusCodeException) ex;
                responseException = new ResponseEntity<>(httpStatusError.getResponseBodyAsString(), httpStatusError.getStatusCode());
            } else {
                //Erro dentro do método de validação Http ou erro "desconhecido", vai criar um erro com status genérico
                responseException = new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Throws criado para obter o erro Http caso exista algum!
            throw new ApiError(
                    RESTClient.class,
                    "sendReceive",
                    "Error to send request to the RiotGames API",
                    UtilsWS.returnErrors(responseException.getBody().toString(), requestApiEnum),
                    responseException.getStatusCode(),
                    endpoint
            );
        }

        return responseEntity;
    }

    public HttpHeaders getHeaders() throws ApiError {
        //Método que cria os headers, no momento cria a chave de autenticação da API
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("X-Riot-Token", "RGAPI-52af9f72-3203-4089-99f0-542312bdf5cf");
            headers.add(HttpHeaders.ACCEPT, "application/json");
            headers.add("Content-Encoding", "gzip, deflate, br");
//            headers.add("Content-Type", "application/json;charset=utf-8");
//            headers.add("Accept-Language","en-US,en;q=0.9,pt-BR;q=0.8,pt;q=0.7");
//            headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        } catch (Exception ex) {
            throw new ApiError(
                    RESTClient.class,
                    "getHeaders",
                    "Error to create new Headers",
                    ex.getLocalizedMessage());
        }

        return headers;
    }

}

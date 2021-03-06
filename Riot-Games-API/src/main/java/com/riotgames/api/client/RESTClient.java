package com.riotgames.api.client;

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
import java.util.Objects;

@Service
public class RESTClient {
    @Autowired
    RestTemplate restTemplate;

    /**
     * @param requestParams  {@link Map} com parâmetros de requisição — Aceita nullable
     * @param uri            Método selecionado da API destino - Aceita nullable
     * @param requestApiEnum Endereço de API destino
     * @return ResponseEntity
     * @throws ApiError classe de exceção RiotGamesClient
     * @apiNote Método que vai enviar requisições para as APIs
     */

    public ResponseEntity<String> sendReceive(Map<String, String> requestParams, String uri, RequestApiEnum requestApiEnum) throws ApiError {
        //Resposta, request, endpoint
        ResponseEntity<String> responseEntity;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        String endpoint = "";

        try {
            //Montando requisição baseada na API desejada
            //Uri passada com base no método
            //Headers para request
            endpoint = UtilsWS.buildUri(requestParams, requestApiEnum != null ? requestApiEnum.getClientAmbiente() : "", uri);
            responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
        } catch (RestClientException ex) {
            //Criação de um responseEntity para enviar o Erro
            ResponseEntity<Object> responseException;

            if (ex instanceof HttpStatusCodeException) {
                //Caso o erro for dessa instancia vai criar o objeto tratado com status
                HttpStatusCodeException httpStatusError = (HttpStatusCodeException) ex;
                responseException = new ResponseEntity<>(httpStatusError.getResponseBodyAsString(), httpStatusError.getStatusCode());
            } else {
                //Erro no método de validação Http ou erro "desconhecido", vai criar um erro com status genérico
                responseException = new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Throws criado para obter o erro Http caso exista algum!
            throw new ApiError(RESTClient.class, "sendReceive", "Error to send request to the RiotGames API", UtilsWS.returnErrors(Objects.requireNonNull(responseException.getBody()).toString(), requestApiEnum), responseException.getStatusCode(), endpoint);
        }

        return responseEntity;
    }

    public HttpHeaders getHeaders() throws ApiError {
        //Método que cria os headers, no momento cria a chave de autenticação da API
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("X-Riot-Token", "RGAPI-c76a9b3a-8c3b-419e-bfb1-ce5144283c86");
            headers.add(HttpHeaders.ACCEPT, "application/json");
            headers.add("Content-Encoding", "gzip, deflate, br");
        } catch (Exception ex) {
            throw new ApiError(RESTClient.class, "getHeaders", "Error to create new Headers", ex.getLocalizedMessage());
        }

        return headers;
    }

}

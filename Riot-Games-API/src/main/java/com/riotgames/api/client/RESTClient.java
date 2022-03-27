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

    //Método que vai enviar requisições para as APIs
    public ResponseEntity<String> sendReceive(String uri, RequestApiEnum requestApiEnum, Class retorno) throws ApiError {
        //Resposta e request
        ResponseEntity<String> responseEntity;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());

        try {
            //Montando requisição baseada na API desejada
            //Uri passada com base no método
            //Headers para request
            responseEntity = restTemplate.exchange(
                    requestApiEnum.getClientAmbiente() + uri,
                    HttpMethod.GET,
                    entity,
                    String.class);
        } catch (RestClientException ex) {
            //Criação de um responseEntity para enviar o Erro
            ResponseEntity<Object> responseException;

            if (ex instanceof HttpStatusCodeException){
               //Caso o erro for dessa instancia vai criar o objeto tratado com status
                HttpStatusCodeException httpStatusError = (HttpStatusCodeException) ex;
//                Object responseError = gson.fromJson(((HttpStatusCodeException) ex).getResponseBodyAsString(), ex.getClass());
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
                    responseException.getBody().toString(),
                    responseException.getStatusCode()
            );
        }

        return responseEntity;
    }

    public HttpHeaders getHeaders() throws ApiError {
        //Método que cria os headers, no momento cria a chave de autenticação da API
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("X-Riot-Token", "RGAPI-939528cf-fd89-4080-8db8-353602b452c6");
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

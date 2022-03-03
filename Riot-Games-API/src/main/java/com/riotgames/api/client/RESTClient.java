package com.riotgames.api.client;

import com.google.gson.Gson;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RESTClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public <T> T sendReceive(String uri, RequestApiEnum requestApiEnum) throws Exception {
        Object result = null;
        String auxEndpoint = null;
        ResponseEntity<String> responseEntity;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());

        try {
            responseEntity = restTemplate.exchange(requestApiEnum.getClientAmbiente() + uri, HttpMethod.GET, entity, String.class);
        } catch (RestClientException ex) {

            ResponseEntity<Object> responseException;

            if (ex instanceof HttpStatusCodeException){

                Object responseError = gson.fromJson(((HttpStatusCodeException) ex).getResponseBodyAsString(), ex.getClass());

                responseException = new ResponseEntity<Object>(responseError, ((HttpStatusCodeException) ex).getStatusCode());
            } else {
                responseException =  new ResponseEntity<Object>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            throw new Exception((String) responseException.getBody());
        }

        return (T) result;
    }

    public HttpHeaders getHeaders() {
        return null;
    }

}

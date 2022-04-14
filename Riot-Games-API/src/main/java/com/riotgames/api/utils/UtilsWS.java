package com.riotgames.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.riotgames.api.client.RESTClient;
import com.riotgames.api.client.RiotgamesClient;
import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.model.error.ErrorJsonApi;
import com.riotgames.api.model.error.ErrorXmlApi;
import com.riotgames.api.model.Status;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class UtilsWS {

    private static Gson gson;

    private static RESTClient restClient;

    private static ObjectMapper objectMapper;

    public static String version;

    public static Object returnErrors(String error, RequestApiEnum requestApiEnum) throws ApiError {
        Object result;

        try {
            if ((requestApiEnum.equals(RequestApiEnum.BR) || requestApiEnum.equals(RequestApiEnum.AMERICAS))) {
                result = buildJsonError(error);
            } else {
                result = buildXmlError(error);
            }
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "returnErrors", "", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    public static Status buildJsonError(String error) throws ApiError {
        ErrorJsonApi errorJsonApis;

        try {
            errorJsonApis = gson.fromJson(error, ErrorJsonApi.class);
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "buildJsonError", "Error to create Object", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new Status(errorJsonApis.getStatus().getMessage(), errorJsonApis.getStatus().getStatus_code());
    }

    public static Object buildXmlError(String error) throws ApiError {
        ObjectMapper objectMapper = new XmlMapper();

        ErrorXmlApi errorXmlApi;

        try {
            errorXmlApi = objectMapper.readValue(error, ErrorXmlApi.class);
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "buildXmlError", "Error to create Object", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return errorXmlApi;
    }

    public static String buildUri(Map<String, ?> requestMap, String url, String uri) throws ApiError {
        UriComponentsBuilder endpoint = UriComponentsBuilder.fromHttpUrl(url + uri);
        ;

        if (requestMap != null && !requestMap.isEmpty()) {

            try {
                for (Map.Entry<String, ?> uriParams : requestMap.entrySet()) {

                    if (uriParams.getValue() != null) {
                        endpoint.queryParam(uriParams.getKey(), uriParams.getValue());
                    }
                }
            } catch (Exception ex) {
                throw new ApiError(UtilsWS.class, "buildUri", "Error to create request params", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return endpoint.toUriString();
    }

    public static void findPatch() throws ApiError {
        ResponseEntity<String> result;
        String[] arrayVersion;

        try {
            result = restClient.sendReceive(null, "api/versions.json", RequestApiEnum.CHAMPION, ResponseEntity.class);

            arrayVersion = objectMapper.readValue(result.getBody(), String[].class);
        } catch (ApiError ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiError(RiotgamesClient.class, "findPatch", "Error to set a last version", ex.getLocalizedMessage());
        }

        version = arrayVersion[0];
    }

    @Autowired
    public void setGson(Gson gson) {
        UtilsWS.gson = gson;
    }

    @Autowired
    public void setRestClient(RESTClient restClient) {
        UtilsWS.restClient = restClient;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        UtilsWS.objectMapper = objectMapper;
    }
}

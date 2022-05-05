package com.riotgames.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.riotgames.api.model.Status;
import com.riotgames.api.model.enumerator.RequestApiEnum;
import com.riotgames.api.model.error.ApiError;
import com.riotgames.api.model.error.ErrorJsonApi;
import com.riotgames.api.model.error.ErrorXmlApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.util.Map;

@Component
public class UtilsWS {

    private static Gson gson;

    private static ObjectMapper objectMapper;

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
            throw new ApiError(UtilsWS.class, "returnErrors", "Error to construct errors api return", ex.getLocalizedMessage());
        }

        return result;
    }

    public static Status buildJsonError(String error) throws ApiError {
        ErrorJsonApi errorJsonApis;

        try {
            errorJsonApis = gson.fromJson(error, ErrorJsonApi.class);
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "buildJsonError", "Error to create Object", ex.getLocalizedMessage());
        }

        return new Status(errorJsonApis.getStatus().getMessage(), errorJsonApis.getStatus().getStatus_code());
    }

    public static Object buildXmlError(String error) throws ApiError {
        ObjectMapper objectMapper = new XmlMapper();

        ErrorXmlApi errorXmlApi;

        try {
            errorXmlApi = objectMapper.readValue(error, ErrorXmlApi.class);
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "buildXmlError", "Error to create Object", ex.getLocalizedMessage());
        }

        return errorXmlApi;
    }

    /**
     * @param requestMap {@link Map} com parâmetros de requisição
     * @param url        API destino
     * @param uri        Método selecionado
     * @return {@link String}
     * @throws ApiError Classe de exceção RiotGamesClient
     * @apiNote Método para montar parâmetros de requisição de forma dinâmica
     */
    public static String buildUri(Map<String, ?> requestMap, String url, String uri) throws ApiError {
        UriComponentsBuilder endpoint = UriComponentsBuilder.fromHttpUrl(url + uri);

        if (requestMap != null && !requestMap.isEmpty()) {

            try {
                for (Map.Entry<String, ?> uriParams : requestMap.entrySet()) {

                    if (uriParams.getValue() != null) {
                        endpoint.queryParam(uriParams.getKey(), uriParams.getValue());
                    }
                }
            } catch (Exception ex) {
                throw new ApiError(UtilsWS.class, "buildUri", "Error to create request params", ex.getLocalizedMessage());
            }

        }
        return endpoint.toUriString();
    }

    public static void saveRequest(String key, String title) throws ApiError {

        try {
            File file = new File("src/main/resources/");


            if (file.exists()) {
                //Lê
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                //Escreve
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                String linha;

                while (bufferedReader.ready()) {
                    linha = bufferedReader.readLine();

                    if (linha.isEmpty() || linha.isBlank()) {
                        bufferedWriter.write(String.format("Localizador: %s, Status de integração: %s", key, title));
                    }
                }

                //Necessário fechar
                bufferedWriter.close();
                fileWriter.close();
                bufferedReader.close();
                fileReader.close();
            } else {
                file.mkdir();
                file.createNewFile();
                saveRequest(key, title);
            }
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "buildUri", "Error to create request params", ex.getLocalizedMessage());
        }

    }

    @Autowired
    public void setGson(Gson gson) {
        UtilsWS.gson = gson;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        UtilsWS.objectMapper = objectMapper;
    }


}

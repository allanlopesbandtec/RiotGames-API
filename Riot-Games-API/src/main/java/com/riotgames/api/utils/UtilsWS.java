package com.riotgames.api.utils;

import com.google.gson.Gson;
import com.riotgames.api.model.ApiError;
import com.riotgames.api.model.ErroJsonApi;
import com.riotgames.api.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UtilsWS {

    private static Gson gson;

    public static Status montaErroJsonApi(String error) throws ApiError {
        ErroJsonApi erroJsonApis;

        try {
            erroJsonApis = gson.fromJson(error, ErroJsonApi.class);
        } catch (Exception ex) {
            throw new ApiError(UtilsWS.class, "montaErroJsonApi", "Error to create error Object", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new Status(erroJsonApis.getStatus().getMessage(), erroJsonApis.getStatus().getStatus_code());
    }

    @Autowired
    public void setGson(Gson gson) {
        UtilsWS.gson = gson;
    }
}

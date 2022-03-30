package com.riotgames.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErroJsonApi {

    @JsonProperty(namespace = "status")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

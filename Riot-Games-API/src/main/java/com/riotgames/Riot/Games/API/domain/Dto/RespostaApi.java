package com.riotgames.Riot.Games.API.domain.Dto;

public class RespostaApi {

    private Status status;

    public RespostaApi(Status status) {
        this.status = status;
    }

    private static class Status{
        String message;
        Integer status_code;

        public Status(String message, Integer status_code) {
            this.message = message;
            this.status_code = status_code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatus_code() {
            return status_code;
        }

        public void setStatus_code(Integer status_code) {
            this.status_code = status_code;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

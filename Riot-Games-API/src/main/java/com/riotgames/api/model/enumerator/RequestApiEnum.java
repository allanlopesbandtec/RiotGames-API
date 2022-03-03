package com.riotgames.api.model.enumerator;

public enum RequestApiEnum {

    BR("https://br1.api.riotgames.com"),
    AMERICAS("https://americas.api.riotgames.com"),
    CHAMPION("http://ddragon.leagueoflegends.com");
    private final String clientAmbiente;

    RequestApiEnum(String clientAmbiente) {
        this.clientAmbiente = clientAmbiente;
    }

    public String getClientAmbiente() {
        return clientAmbiente;
    }
}

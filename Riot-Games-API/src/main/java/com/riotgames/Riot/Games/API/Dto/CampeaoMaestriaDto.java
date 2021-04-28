package com.riotgames.Riot.Games.API.Dto;

public class CampeaoMaestriaDto {

    private String nome;

    private Integer pontos;

    private Integer nivel;

    public CampeaoMaestriaDto(String nome, Integer pontos, Integer nivel) {
        this.nome = nome;
        this.pontos = pontos;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}

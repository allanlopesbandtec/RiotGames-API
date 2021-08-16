package com.riotgames.Riot.Games.API.domain.Dto;

public class CampeaoMaestriaDto {

    private Long key;

    private String nomeCampeao;

    private Integer pontosDeMaestria;

    private Integer nivelMaestria;

    private Boolean ganhouBau;

    public CampeaoMaestriaDto(CampeaoPorMaestria campeaoPorMaestria, CampeaoDto campeao) {
        this.key = campeaoPorMaestria.getIdCampeao();
        this.nomeCampeao = campeao.getName();
        this.pontosDeMaestria = campeaoPorMaestria.getPontosComCampeao();
        this.nivelMaestria = campeaoPorMaestria.getNivelCampeao();
        this.ganhouBau = campeaoPorMaestria.getBauHabilitado();
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNomeCampeao() {
        return nomeCampeao;
    }

    public void setNomeCampeao(String nomeCampeao) {
        this.nomeCampeao = nomeCampeao;
    }

    public Integer getPontosDeMaestria() {
        return pontosDeMaestria;
    }

    public void setPontosDeMaestria(Integer pontosDeMaestria) {
        this.pontosDeMaestria = pontosDeMaestria;
    }

    public Integer getNivelMaestria() {
        return nivelMaestria;
    }

    public void setNivelMaestria(Integer nivelMaestria) {
        this.nivelMaestria = nivelMaestria;
    }

    public Boolean getGanhouBau() {
        return ganhouBau;
    }

    public void setGanhouBau(Boolean ganhouBau) {
        this.ganhouBau = ganhouBau;
    }
}

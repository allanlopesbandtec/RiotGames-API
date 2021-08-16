package com.riotgames.Riot.Games.API.domain.Dto;

public class CampeaoPorMaestria {

    private Long championPointsUntilNextLevel;

    private Boolean chestGranted;

    private Long championId;

    private Long lastPlayTime;

    private Integer championLevel;

    private String summonerId;

    private Integer championPoints;

    private Long championPointsSinceLastLevel;

    private Integer tokensEarned;

    public CampeaoPorMaestria(Long championPointsUntilNextLevel, Boolean chestGranted, Long championId, Long lastPlayTime, Integer championLevel, String summonerId, Integer championPoints, Long championPointsSinceLastLevel, Integer tokensEarned) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
        this.chestGranted = chestGranted;
        this.championId = championId;
        this.lastPlayTime = lastPlayTime;
        this.championLevel = championLevel;
        this.summonerId = summonerId;
        this.championPoints = championPoints;
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
        this.tokensEarned = tokensEarned;
    }

    public Long getPontosparaOProximoNivelCampeao() {
        return championPointsUntilNextLevel;
    }

    public Boolean getBauHabilitado() {
        return chestGranted;
    }

    public Long getIdCampeao() {
        return championId;
    }

    public Long getUltimaVezJogado() {
        return lastPlayTime;
    }

    public Integer getNivelCampeao() {
        return championLevel;
    }

    public String getIdInvocador() {
        return summonerId;
    }

    public Integer getPontosComCampeao() {
        return championPoints;
    }

    public Long getPontosGanhosDesdeUltimoNivel() {
        return championPointsSinceLastLevel;
    }


    public Integer getQuatidadeS() {
        return tokensEarned;
    }
}

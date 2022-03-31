package com.riotgames.api.model.Dto;

public class ChampionByMastery {

    private final Long championPointsUntilNextLevel;

    private final Boolean chestGranted;

    private final Long championId;

    private final Long lastPlayTime;

    private final Integer championLevel;

    private final String summonerId;

    private final Integer championPoints;

    private final Long championPointsSinceLastLevel;

    private final Integer tokensEarned;

    public ChampionByMastery(Long championPointsUntilNextLevel, Boolean chestGranted, Long championId, Long lastPlayTime, Integer championLevel, String summonerId, Integer championPoints, Long championPointsSinceLastLevel, Integer tokensEarned) {
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

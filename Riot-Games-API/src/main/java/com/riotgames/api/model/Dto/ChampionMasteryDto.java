package com.riotgames.api.model.Dto;

public class ChampionMasteryDto {

    private Long key;

    private String championName;

    private Integer masteryPoints;

    private Integer masteryLevel;

    private Boolean chestWinned;

    public ChampionMasteryDto(ChampionByMastery championByMastery, ChampionDto champion) {
        this.key = championByMastery.getChampionId();
        this.championName = champion.getName();
        this.masteryPoints = championByMastery.getChampionPoints();
        this.masteryLevel = championByMastery.getChampionLevel();
        this.chestWinned = championByMastery.getChestGranted();
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public Integer getMasteryPoints() {
        return masteryPoints;
    }

    public void setMasteryPoints(Integer masteryPoints) {
        this.masteryPoints = masteryPoints;
    }

    public Integer getMasteryLevel() {
        return masteryLevel;
    }

    public void setMasteryLevel(Integer masteryLevel) {
        this.masteryLevel = masteryLevel;
    }

    public Boolean getChestWinned() {
        return chestWinned;
    }

    public void setChestWinned(Boolean chestWinned) {
        this.chestWinned = chestWinned;
    }
}

package com.riotgames.api.model;

public class Summoner {

    private String id;

    private String accountId;

    private String puuid;

    private String name;

    private Integer profileIconId;

    private Long revisionDate;

    private Integer summonerLevel;

    public Summoner(String id, String accountId, String puuid, String name, Integer profileIconId, Long revisionDate, Integer summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdConta() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuId() {
        return puuid;
    }

    public void setPuuId(String puuId) {
        this.puuid = puuId;
    }

    public String getNick() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdIconePerfil() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Long getDataRevisao() {
        return revisionDate;
    }

    public void setRevisionDate(Long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Integer getLevelInvocador() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}

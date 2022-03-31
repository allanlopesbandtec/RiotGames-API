package com.riotgames.api.model;

import com.riotgames.api.model.match.Team;

import java.util.List;

public class Match {

    private String matchId;

    private List<String> participants;

    private String platformId;

    private Integer queueId;

    private List<Team> teams;

    public Match(String matchId, List<String> participants, String platformId, Integer queueId, List<Team> teams) {
        this.matchId = matchId;
        this.participants = participants;
        this.platformId = platformId;
        this.queueId = queueId;
        this.teams = teams;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}

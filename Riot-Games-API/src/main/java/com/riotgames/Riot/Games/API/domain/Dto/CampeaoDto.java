package com.riotgames.Riot.Games.API.domain.Dto;

import com.riotgames.Riot.Games.API.domain.Campeao;

import java.util.List;

public class CampeaoDto {

    private String key;

    private String name;

    private List<String> tags;

    public CampeaoDto(Campeao campeao) {
        this.key = campeao.getKey();
        this.name = campeao.getName();
        this.tags = campeao.getTags();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

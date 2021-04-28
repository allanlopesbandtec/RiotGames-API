package com.riotgames.Riot.Games.API.domain;
import com.riotgames.Riot.Games.API.Dto.Campeao;

import java.util.List;

public class TodosChamps {

    private String type;

    private String format;

    private String version;

    private List<Campeao> data;

    public TodosChamps(String type, String format, String version, List<Campeao> data) {
        this.type = type;
        this.format = format;
        this.version = version;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Campeao> getData() {
        return data;
    }

    public void setData(List<Campeao> data) {
        this.data = data;
    }
}

package com.riotgames.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riotgames.api.model.champion.Image;
import com.riotgames.api.model.champion.Info;
import com.riotgames.api.model.champion.Stats;

import java.util.List;


public class Champion {

    @JsonProperty
    private String version;

    @JsonProperty
    private String id;

    @JsonProperty
    private String key;

    @JsonProperty
    private String name;

    @JsonProperty
    private String title;

    @JsonProperty
    private String blurb;

    @JsonProperty
    private Info info;

    @JsonProperty
    private Image image;

    @JsonProperty
    private List<String> tags;

    @JsonProperty
    private String partType;

    @JsonProperty
    private Stats stats;

    public Champion(String version, String id, String key, String name, String title, String blurb, Info info, Image image, List<String> tags, String partType, Stats stats) {
        this.version = version;
        this.id = id;
        this.key = key;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
        this.info = info;
        this.image = image;
        this.tags = tags;
        this.partType = partType;
        this.stats = stats;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}

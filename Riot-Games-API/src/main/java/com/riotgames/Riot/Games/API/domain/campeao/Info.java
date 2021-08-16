package com.riotgames.Riot.Games.API.domain.campeao;

public class Info {

    private Integer attack;

    private Integer defense;

    private Integer magic;

    private Integer difficulty;

    public Info(Integer attack, Integer defense, Integer magic, Integer difficulty) {
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}

package com.riotgames.api.model.match;

public class Selection {

    private Integer perk;

    private Integer var1;

    private Integer var2;

    private Integer var3;

    public Selection(Integer perk, Integer var1, Integer var2, Integer var3) {
        this.perk = perk;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

    public Integer getPerk() {
        return perk;
    }

    public void setPerk(Integer perk) {
        this.perk = perk;
    }

    public Integer getVar1() {
        return var1;
    }

    public void setVar1(Integer var1) {
        this.var1 = var1;
    }

    public Integer getVar2() {
        return var2;
    }

    public void setVar2(Integer var2) {
        this.var2 = var2;
    }

    public Integer getVar3() {
        return var3;
    }

    public void setVar3(Integer var3) {
        this.var3 = var3;
    }
}

package co.com.lkm.shen.vampirehelper.Domain;

import io.realm.RealmObject;

public class Player extends RealmObject {

    private String name;
    private String characterName;
    private String clan;
    private int stamina;
    private int Initiative;
    private int bashing;
    private int lethal;
    private int aggravated;
    private int totalDamage;

    private boolean dazed;
    private boolean dead;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getInitiative() {
        return Initiative;
    }

    public void setInitiative(int initiative) {
        Initiative = initiative;
    }

    public int getBashing() {
        return bashing;
    }

    public void setBashing(int bashing) {
        /*int newDamage = this.totalDamage + bashing;
        if(newDamage > 8) {
            setLethal(newDamage-7);
            this.bashing = Math.abs(7-newDamage);
        } else {
            this.bashing += bashing;
        }*/
        this.bashing = bashing;
    }

    public int getLethal() {
        return lethal;
    }

    public void setLethal(int lethal) {
       this.lethal = lethal;
    }

    public int getAggravated() {
        return aggravated;
    }

    public void setAggravated(int aggravated) {
       this.aggravated = aggravated;
    }

    public boolean isDazed() {
        return dazed;
    }

    public void setDazed(boolean dazed) {
        this.dazed = dazed;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }
}

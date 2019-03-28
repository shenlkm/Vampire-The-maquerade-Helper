package co.com.lkm.shen.vampirehelper.Data.Domain.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "player")
public class Player{


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "characterName")
    private String characterName;
    @ColumnInfo(name = "clan")
    private int clan;
    @ColumnInfo(name = "stamina")
    private int stamina;
    @ColumnInfo(name = "initiative")
    private int initiative;
    @ColumnInfo(name = "bashing")
    private int bashing;
    @ColumnInfo(name = "lethal")
    private int lethal;
    @ColumnInfo(name = "aggravated")
    private int aggravated;
    @ColumnInfo(name = "totalDamage")
    private int totalDamage;
    @ColumnInfo(name = "dazed")
    private boolean dazed;
    @ColumnInfo(name = "dead")
    private boolean dead;
    @ColumnInfo(name = "chronicle_id")
    private Long ChronicleId;

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    @ColumnInfo(name = "is_player")
    private boolean isPlayer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getClan() {
        return clan;
    }

    public void setClan(int clan) {
        this.clan = clan;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
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

    public Long getChronicleId() {
        return ChronicleId;
    }

    public void setChronicleId(Long chronicleId) {
        ChronicleId = chronicleId;
    }
}

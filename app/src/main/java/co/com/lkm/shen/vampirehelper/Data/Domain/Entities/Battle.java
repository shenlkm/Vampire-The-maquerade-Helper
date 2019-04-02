package co.com.lkm.shen.vampirehelper.Data.Domain.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "battle",
        foreignKeys = {@ForeignKey(entity = Scene.class, parentColumns = "id", childColumns = "scene_id"),
                       @ForeignKey(entity = Player.class, parentColumns = "id", childColumns = "chatacter_id")},
        indices = {@Index("id"), @Index("chatacter_id"), @Index("scene_id")})
public class Battle {

    public Battle(Long sceneId, Long characterId){
        this.sceneId = sceneId;
        this.characterId = characterId;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Long id;
    @ColumnInfo(name = "scene_id")
    private Long sceneId;
    @ColumnInfo(name = "chatacter_id")
    private Long characterId;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }
}

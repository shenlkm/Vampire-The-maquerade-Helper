package co.com.lkm.shen.vampirehelper.Data.Domain.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;

@Dao
public interface BattleDao {

    @Insert
    public void insert(Battle...chronicles);

    @Update
    public void update(Battle...chronicles);

    @Delete
    public void delete(Battle...chronicles);

    @Query("SELECT player.* FROM player " +
                            "INNER JOIN battle ON player.id = battle.chatacter_id " +
                            "INNER JOIN scene ON battle.scene_id = scene.id " +
                            "WHERE scene.id = :sceneId")
    public LiveData<List<Player>> getAllPlayersInBattle(Long sceneId);
}

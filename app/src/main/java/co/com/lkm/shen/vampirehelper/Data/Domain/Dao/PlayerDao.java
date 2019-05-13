package co.com.lkm.shen.vampirehelper.Data.Domain.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;

@Dao
public interface PlayerDao {
    @Insert
    public void insert(Player...players);

    @Update
    public void update(Player...players);

    @Insert
    public void delete(Player...players);

    @Query("SELECT * FROM player WHERE chronicle_id = :mChronicleID")
    public LiveData<List<Player>> getChroniclePlayer(Long mChronicleID);
}

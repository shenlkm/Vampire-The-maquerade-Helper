package co.com.lkm.shen.vampirehelper.Data.Domain.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;

@Dao
public interface SceneDao {

    @Insert
    public void insert(Scene...scenes);

    @Update
    public void update(Scene...scenes);

    @Insert
    public void delete(Scene...scenes);

    @Query("SELECT * FROM scene WHERE chronicle_id = :mChronicleID")
    public LiveData<List<Scene>> getChronicleScenes(Long mChronicleID);

    @Query("SELECT * FROM scene WHERE id = :mId")
    public LiveData<Scene> getSceneById(int mId);
}

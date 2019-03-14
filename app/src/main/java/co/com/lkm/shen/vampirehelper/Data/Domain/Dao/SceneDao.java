package co.com.lkm.shen.vampirehelper.Data.Domain.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
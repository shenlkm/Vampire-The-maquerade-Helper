package co.com.lkm.shen.vampirehelper.Data.Repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;

public interface SceneRepository extends BaseRepository<Scene>{

    LiveData<List<Scene>> getChronicleScenes(Long mChronicleID);

    LiveData<Scene> getSceneById(int mId);
}

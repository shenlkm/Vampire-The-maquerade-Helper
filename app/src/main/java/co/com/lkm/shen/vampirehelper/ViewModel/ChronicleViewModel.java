package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.Data.Repository.PlayerRepository;
import co.com.lkm.shen.vampirehelper.Data.Repository.SceneRepository;

public class ChronicleViewModel extends AndroidViewModel {

    public LiveData<List<Scene>> Scenes;
    public LiveData<List<Player>> Players;
    public SceneRepository mSceneRepository;
    public PlayerRepository mPlayerRepository;

    @Inject
    public ChronicleViewModel(@NonNull SceneRepository sceneRepository, @NonNull PlayerRepository playerRepository, @NonNull Application application) {
        super(application);
        mSceneRepository = sceneRepository;
        mPlayerRepository = playerRepository;
    }

    public LiveData<List<Scene>> getChronicleScenes(Long id) {
        Scenes = mSceneRepository.getChronicleScenes(id);
        return Scenes;
    }

    public LiveData<List<Player>> getChroniclePlayers(Long id) {
        Players = mPlayerRepository.getChroniclePlayers(id);
        return Players;
    }

    public void  insert(Scene scene){
        mSceneRepository.insert(scene);
    }

}

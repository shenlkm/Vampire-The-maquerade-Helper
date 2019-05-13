package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public void setChronicle_id(Long chronicle_id) {
        this.chronicle_id = chronicle_id;
    }

    private Long chronicle_id;

    @Inject
    public ChronicleViewModel(@NonNull SceneRepository sceneRepository, @NonNull PlayerRepository playerRepository, @NonNull Application application) {
        super(application);
        mSceneRepository = sceneRepository;
        mPlayerRepository = playerRepository;
    }

    public LiveData<List<Scene>> getChronicleScenes(Long chronicle_id) {
        if(Scenes == null){
            Scenes = mSceneRepository.getChronicleScenes(chronicle_id);
        }
        return Scenes;
    }

    public LiveData<List<Player>> getChroniclePlayers(Long chronicle_id) {
        if(Players == null){
            Players = mPlayerRepository.getChroniclePlayers(chronicle_id);
        }
        return Players;
    }

    public void  insert(Scene scene){
        mSceneRepository.insert(scene);
    }

}

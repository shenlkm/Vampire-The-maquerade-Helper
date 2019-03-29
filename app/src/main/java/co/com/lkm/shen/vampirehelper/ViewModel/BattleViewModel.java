package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Repository.PlayerRepository;

public class BattleViewModel extends AndroidViewModel {

    public LiveData<List<Player>> Players;
    public PlayerRepository mPlayerRepository;

    @Inject
    public BattleViewModel(@NonNull PlayerRepository playerRepository, @NonNull Application application) {
        super(application);
        mPlayerRepository = playerRepository;
    }

    public LiveData<List<Player>> getChroniclePlayers(Long chronicle_id){
        if(Players == null){
            Players = mPlayerRepository.getChroniclePlayers(chronicle_id);
        }
        return Players;
    }
}

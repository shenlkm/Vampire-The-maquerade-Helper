package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Repository.BattleRepository;
import co.com.lkm.shen.vampirehelper.Data.Repository.PlayerRepository;

public class BattleViewModel extends AndroidViewModel {

    public LiveData<List<Player>> Players;
    public PlayerRepository mPlayerRepository;
    public BattleRepository mBattleRepository;

    @Inject
    public BattleViewModel(@NonNull PlayerRepository playerRepository, @NonNull BattleRepository battleRepository, @NonNull Application application) {
        super(application);
        mPlayerRepository = playerRepository;
        mBattleRepository = battleRepository;
    }

    public LiveData<List<Player>> getChroniclePlayers(Long chronicle_id){
        if(Players == null){
            Players = mPlayerRepository.getChroniclePlayers(chronicle_id);
        }
        return Players;
    }

    public LiveData<List<Player>> getChroniclePlayersOnBattle(Long scene_id){
        if(Players == null){
            Players = mBattleRepository.getAllPlayersInBattle(scene_id);
        }
        return Players;
    }

    public void insert(Battle...items){
        mBattleRepository.insert(items);
    }
}

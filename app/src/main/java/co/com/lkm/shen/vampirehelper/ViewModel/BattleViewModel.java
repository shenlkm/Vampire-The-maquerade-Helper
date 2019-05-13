package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Repository.BattleRepository;
import co.com.lkm.shen.vampirehelper.Data.Repository.PlayerRepository;

public class BattleViewModel extends AndroidViewModel {

    public LiveData<List<Player>> choosablePlayer;
    public LiveData<List<Player>> battlePlayer;
    public PlayerRepository mPlayerRepository;
    public BattleRepository mBattleRepository;

    @Inject
    public BattleViewModel(@NonNull PlayerRepository playerRepository, @NonNull BattleRepository battleRepository, @NonNull Application application) {
        super(application);
        mPlayerRepository = playerRepository;
        mBattleRepository = battleRepository;
    }

    public LiveData<List<Player>> getChroniclePlayers(Long chronicle_id){
        choosablePlayer = mPlayerRepository.getChroniclePlayers(chronicle_id);
        return choosablePlayer;
    }

    public LiveData<List<Player>> getChroniclePlayersOnBattle(Long scene_id){
        battlePlayer = mBattleRepository.getAllPlayersInBattle(scene_id);
        return battlePlayer;
    }

    public void insert(Battle...items){
        mBattleRepository.insert(items);
    }
}

package co.com.lkm.shen.vampirehelper.Data.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;

public interface BattleRepository extends BaseRepository<Battle> {

    public LiveData<List<Player>> getAllPlayersInBattle(Long sceneId);

}

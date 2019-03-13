package co.com.lkm.shen.vampirehelper.Data.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;

public interface PlayerRepository extends BaseRepository<Player> {

    LiveData<List<Player>> getChroniclePlayers(Long mChronicleID);
}

package co.com.lkm.shen.vampirehelper.Data.Repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;

public interface PlayerRepository extends BaseRepository<Player> {

    LiveData<List<Player>> getChroniclePlayers(Long mChronicleID);
}

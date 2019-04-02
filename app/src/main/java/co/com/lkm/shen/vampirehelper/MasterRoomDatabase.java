package co.com.lkm.shen.vampirehelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import co.com.lkm.shen.vampirehelper.Data.Converters.Converters;
import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.BattleDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.ChronicleDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.PlayerDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.SceneDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;

@Database(
        entities = {
                Chronicle.class,
                Scene.class,
                Player.class,
                Battle.class
        },
        version = MasterRoomDatabase.VERSION)
@TypeConverters({Converters.class})
public abstract class MasterRoomDatabase extends RoomDatabase {
    public abstract ChronicleDao chronicleDao();
    public abstract SceneDao sceneDao();
    public abstract PlayerDao playerDao();
    public abstract BattleDao battleDao();
    static final int VERSION = 1;
}

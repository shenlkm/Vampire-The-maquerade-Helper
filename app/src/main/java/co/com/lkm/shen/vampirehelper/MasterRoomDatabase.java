package co.com.lkm.shen.vampirehelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import co.com.lkm.shen.vampirehelper.Repository.Domain.Entities.Chronicle;
import co.com.lkm.shen.vampirehelper.Repository.Domain.Dao.ChronicleDao;

@Database(
        entities = {Chronicle.class
        },
        version = MasterRoomDatabase.VERSION)
public abstract class MasterRoomDatabase extends RoomDatabase {
    public abstract ChronicleDao chronicleDao();
    static final int VERSION = 1;
}

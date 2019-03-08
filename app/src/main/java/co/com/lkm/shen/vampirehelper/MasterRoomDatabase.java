package co.com.lkm.shen.vampirehelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Dao.ChronicleDao;

@Database(
        entities = {Chronicle.class
        },
        version = 1)
public abstract class MasterRoomDatabase extends RoomDatabase {
    public abstract ChronicleDao chronicleDao();

    private static volatile MasterRoomDatabase INSTANCE;

    public static MasterRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null)
            synchronized (MasterRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MasterRoomDatabase.class, "master_database").build();
                }
            }
        return INSTANCE;
    }
}

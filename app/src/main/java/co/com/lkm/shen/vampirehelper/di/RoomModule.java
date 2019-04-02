package co.com.lkm.shen.vampirehelper.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import co.com.lkm.shen.vampirehelper.Data.DataSource.*;
import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.*;
import co.com.lkm.shen.vampirehelper.Data.Repository.*;
import co.com.lkm.shen.vampirehelper.MasterRoomDatabase;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private MasterRoomDatabase mDatabase;

    public RoomModule(Application mApplication){
        mDatabase = Room.databaseBuilder(mApplication.getApplicationContext(), MasterRoomDatabase.class, "master_database").build();
    }

    @Singleton
    @Provides
    MasterRoomDatabase providesDataBase(){
        return mDatabase;
    }

    @Singleton
    @Provides
    ChronicleDao providesChronicleDao(MasterRoomDatabase database){
        return mDatabase.chronicleDao();
    }

    @Singleton
    @Provides
    ChronicleRepository providesChronicleRepository(ChronicleDao chronicleDao){
        return new ChronicleDataSource(chronicleDao);
    }

    @Singleton
    @Provides
    SceneDao providesSceneDao(MasterRoomDatabase database){
        return mDatabase.sceneDao();
    }

    @Singleton
    @Provides
    SceneRepository providesSceneRepository(SceneDao sceneDao){
        return new SceneDataSource(sceneDao);
    }

    @Singleton
    @Provides
    PlayerDao providesPlayerDao(MasterRoomDatabase database){
        return mDatabase.playerDao();
    }

    @Singleton
    @Provides
    PlayerRepository providesPlayerRepository(PlayerDao playerDao){
        return new PlayerDataSource(playerDao);
    }

    @Singleton
    @Provides
    BattleDao providesBattleDao(MasterRoomDatabase database){
        return mDatabase.battleDao();
    }

    @Singleton
    @Provides
    BattleRepository providesBattleRepository(BattleDao battleDao){
        return new BattleDataSource(battleDao);
    }

}

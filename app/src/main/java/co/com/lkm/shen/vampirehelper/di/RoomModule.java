package co.com.lkm.shen.vampirehelper.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import co.com.lkm.shen.vampirehelper.Repository.DataSource.ChronicleDataSource;
import co.com.lkm.shen.vampirehelper.Repository.Domain.Dao.ChronicleDao;
import co.com.lkm.shen.vampirehelper.MasterRoomDatabase;
import co.com.lkm.shen.vampirehelper.Repository.ChronicleRepository;
import dagger.BindsInstance;
import dagger.Component;
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
}

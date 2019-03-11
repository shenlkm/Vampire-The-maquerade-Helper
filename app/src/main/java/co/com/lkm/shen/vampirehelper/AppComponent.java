package co.com.lkm.shen.vampirehelper;

import android.app.Application;

import javax.inject.Singleton;

import co.com.lkm.shen.vampirehelper.Domain.Dao.ChronicleDao;
import co.com.lkm.shen.vampirehelper.Repository.ChronicleRepository;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(dependencies = {}, modules = {AndroidInjectionModule.class, AppModule.class, RoomModule.class})
interface AppComponent {

    void inject(VampireHelper vampireHelper);

    Application application();
    ChronicleDao chronicleDao();
    MasterRoomDatabase masterRoomDatabase();
    ChronicleRepository chronicleRepository();
}

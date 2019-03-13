package co.com.lkm.shen.vampirehelper.di;

import android.app.Application;

import javax.inject.Singleton;

import co.com.lkm.shen.vampirehelper.VampireHelper;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules =
        {
                AndroidInjectionModule.class,
                AppModule.class,
                RoomModule.class,
                HomeActivityModule.class,
                ChronicleActivityModule.class
        })
interface AppComponent {

    void inject(VampireHelper vampireHelper);

    @Component.Builder
    interface  Builder{
        @BindsInstance Builder application(Application application);
        AppComponent build();
        Builder AppModule(AppModule am);
        Builder RoomModule(RoomModule rm);
    }
}

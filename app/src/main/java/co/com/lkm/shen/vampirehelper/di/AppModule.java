package co.com.lkm.shen.vampirehelper.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import co.com.lkm.shen.vampirehelper.ViewModel.MasterViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent){
        return  new MasterViewModelFactory(viewModelSubComponent.build());
    }
}

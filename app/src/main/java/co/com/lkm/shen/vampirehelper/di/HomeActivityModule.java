package co.com.lkm.shen.vampirehelper.di;

import co.com.lkm.shen.vampirehelper.View.HomeActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract HomeActivity contributeHomeActivity();
}

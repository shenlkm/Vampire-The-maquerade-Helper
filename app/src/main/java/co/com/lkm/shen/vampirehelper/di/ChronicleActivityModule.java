package co.com.lkm.shen.vampirehelper.di;

import co.com.lkm.shen.vampirehelper.View.ChronicleActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChronicleActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract ChronicleActivity contributeChronicleActivity();
}

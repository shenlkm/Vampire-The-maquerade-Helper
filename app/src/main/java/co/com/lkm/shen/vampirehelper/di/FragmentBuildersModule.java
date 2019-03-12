package co.com.lkm.shen.vampirehelper.di;

import co.com.lkm.shen.vampirehelper.Fragments.ChronicleFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ChronicleFragment contributeChronicleFragment();
}

package co.com.lkm.shen.vampirehelper.di;

import co.com.lkm.shen.vampirehelper.View.Fragments.BattleFragment;
import co.com.lkm.shen.vampirehelper.View.Fragments.CharacterFragment;
import co.com.lkm.shen.vampirehelper.View.Fragments.ChronicleFragment;
import co.com.lkm.shen.vampirehelper.View.Fragments.CreateCharacterFragment;
import co.com.lkm.shen.vampirehelper.View.Fragments.DashboardFragment;
import co.com.lkm.shen.vampirehelper.View.Fragments.SceneFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ChronicleFragment contributeChronicleFragment();
    @ContributesAndroidInjector
    abstract SceneFragment contributeSceneFragment();
    @ContributesAndroidInjector
    abstract CharacterFragment contributeCharacterFragment();
    @ContributesAndroidInjector
    abstract CreateCharacterFragment contributeCreateCharacterFragment();
    @ContributesAndroidInjector
    abstract DashboardFragment contributeDashboardFragment();
    @ContributesAndroidInjector
    abstract BattleFragment contributeBattleFragment();
}

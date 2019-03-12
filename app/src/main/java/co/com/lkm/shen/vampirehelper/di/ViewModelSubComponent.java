package co.com.lkm.shen.vampirehelper.di;

import co.com.lkm.shen.vampirehelper.ViewModel.ChronicleViewModel;
import co.com.lkm.shen.vampirehelper.ViewModel.HomeViewModel;
import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder{
        ViewModelSubComponent build();
    }

    HomeViewModel homeViewModel();
    //ChronicleViewModel chronicleViewModel();
}

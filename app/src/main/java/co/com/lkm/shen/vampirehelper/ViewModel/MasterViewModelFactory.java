package co.com.lkm.shen.vampirehelper.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.ArrayMap;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.lkm.shen.vampirehelper.di.ViewModelSubComponent;

@Singleton
public class MasterViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public MasterViewModelFactory(final ViewModelSubComponent viewModelSubComponent){
        creators = new ArrayMap<>();

        creators.put(HomeViewModel.class, () -> viewModelSubComponent.homeViewModel() );
        creators.put(ChronicleViewModel.class, () -> viewModelSubComponent.chronicleViewModel() );
        creators.put(CreateCharacterViewModel.class, () -> viewModelSubComponent.createCharacterViewModel() );
        creators.put(BattleViewModel.class, () -> viewModelSubComponent.battleViewModel() );
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null){
            for(Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()){
                if(modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if(creator == null){
            throw new IllegalArgumentException("Unkwown model class" + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

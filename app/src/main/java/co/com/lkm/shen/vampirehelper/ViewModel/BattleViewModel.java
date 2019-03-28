package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class BattleViewModel extends AndroidViewModel {

    @Inject
    public BattleViewModel(@NonNull Application application) {
        super(application);
    }
}

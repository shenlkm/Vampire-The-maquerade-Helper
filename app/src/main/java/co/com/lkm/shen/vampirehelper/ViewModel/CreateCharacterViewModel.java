package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class CreateCharacterViewModel extends AndroidViewModel {

    @Inject
    public CreateCharacterViewModel(@NonNull Application application) {
        super(application);
    }
}

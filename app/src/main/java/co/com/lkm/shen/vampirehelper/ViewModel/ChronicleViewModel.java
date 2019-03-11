package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import co.com.lkm.shen.vampirehelper.DataSource.ChronicleDataSource;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Scene;

public class ChronicleViewModel extends AndroidViewModel {

    public LiveData<List<Scene>> Scenes;
    private ChronicleDataSource mChronicleDataSource;

    public ChronicleViewModel(@NonNull Application application) {
        super(application);

        //mChronicleDataSource = ChronicleDataSource.getInstance();
    }

    public LiveData<List<Scene>> getChronicles(int id) {
        //Scenes = mChronicleDataSource.getChronicleScenes(id);
        return Scenes;
    }

    public void  insert(Chronicle chronicle){
        mChronicleDataSource.insert(chronicle);
    }

}

package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.DataSource.ChronicleDataSource;
import co.com.lkm.shen.vampirehelper.Repository.ChronicleRepository;

public class HomeViewModel extends AndroidViewModel {

    public LiveData<List<Chronicle>> Chronicles;

    @Inject
    public ChronicleRepository mChronicleDataSource;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        //Chronicles = mChronicleDataSource.getAllChronicles();
    }

    public LiveData<List<Chronicle>> getChronicles() {
        return null;
    }

    public void  insert(Chronicle chronicle){
        mChronicleDataSource.insert(chronicle);
    }
}

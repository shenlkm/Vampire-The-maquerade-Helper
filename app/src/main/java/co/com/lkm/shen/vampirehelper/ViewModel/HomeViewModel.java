package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;
import co.com.lkm.shen.vampirehelper.Data.Repository.ChronicleRepository;

public class HomeViewModel extends AndroidViewModel {

    public LiveData<List<Chronicle>> Chronicles;
    public ChronicleRepository mChronicleDataSource;

    @Inject
    public HomeViewModel(@NonNull ChronicleRepository chronicleRepository, @NonNull Application application) {
        super(application);
        mChronicleDataSource = chronicleRepository;
        Chronicles = mChronicleDataSource.getAllChronicles();
    }

    public LiveData<List<Chronicle>> getChronicles() {
        return Chronicles;
    }

    public void  insert(Chronicle chronicle){
        mChronicleDataSource.insert(chronicle);
    }
}

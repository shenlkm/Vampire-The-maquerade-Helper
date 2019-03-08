package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.MasterRepository;
import co.com.lkm.shen.vampirehelper.MasterRoomDatabase;

public class HomeViewModel extends AndroidViewModel {

    public LiveData<List<Chronicle>> Chronicles;
    private MasterRepository mMasterRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mMasterRepository = MasterRepository.getInstance(MasterRoomDatabase.getDatabase(application));
        Chronicles = mMasterRepository.getAllChronicles();
    }

    public LiveData<List<Chronicle>> getChronicles() {
        return Chronicles;
    }

    public void  insert(Chronicle chronicle){
        mMasterRepository.insert(chronicle);
    }
}

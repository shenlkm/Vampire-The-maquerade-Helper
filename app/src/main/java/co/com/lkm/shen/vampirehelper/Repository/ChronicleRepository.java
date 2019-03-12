package co.com.lkm.shen.vampirehelper.Repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Repository.Domain.Entities.Chronicle;

public interface ChronicleRepository {

    LiveData<List<Chronicle>> getAllChronicles();

    void insert (Chronicle chronicle);

}

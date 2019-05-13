package co.com.lkm.shen.vampirehelper.Data.Repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;

public interface ChronicleRepository extends BaseRepository<Chronicle> {

    LiveData<List<Chronicle>> getAllChronicles();
}

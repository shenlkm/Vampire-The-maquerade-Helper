package co.com.lkm.shen.vampirehelper.Repository.Domain.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Repository.Domain.Entities.Chronicle;

@Dao
public interface ChronicleDao {

    @Insert
    public void insert(Chronicle...chronicles);

    @Update
    public void update(Chronicle...chronicles);

    @Delete
    public void delete(Chronicle...chronicles);

    @Query("SELECT * FROM chronicle")
    public LiveData<List<Chronicle>>  getAllChronicles();

    @Query("SELECT * FROM chronicle WHERE id = :mId")
    public LiveData<Chronicle> getChronicle(int mId);
}

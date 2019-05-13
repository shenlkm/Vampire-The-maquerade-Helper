package co.com.lkm.shen.vampirehelper.Data.Domain.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;

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

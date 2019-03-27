package co.com.lkm.shen.vampirehelper.Data.Repository;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

public interface BaseRepository<T> {
    @Insert
    public void insert(T...items);

    @Update
    public void update(T...items);

    @Insert
    public void delete(T...items);
}

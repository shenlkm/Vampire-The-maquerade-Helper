package co.com.lkm.shen.vampirehelper;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Dao.ChronicleDao;

public class MasterRepository {

    private MasterRoomDatabase mDataBase;
    private static MasterRepository sInstance;


    MasterRepository(MasterRoomDatabase db){
        mDataBase = db;
    }

    public static MasterRepository getInstance(final MasterRoomDatabase database) {
        if (sInstance == null) {
            synchronized (MasterRepository.class) {
                if (sInstance == null) {
                    sInstance = new MasterRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Chronicle>> getAllChronicles(){
        return mDataBase.chronicleDao().getAllChronicles();
    }

    public void insert (Chronicle chronicle) {
        new insertAsyncTask(mDataBase.chronicleDao()).execute(chronicle);
    }

    private static class insertAsyncTask extends AsyncTask<Chronicle, Void, Void> {

        private ChronicleDao mAsyncTaskDao;

        insertAsyncTask(ChronicleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Chronicle... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

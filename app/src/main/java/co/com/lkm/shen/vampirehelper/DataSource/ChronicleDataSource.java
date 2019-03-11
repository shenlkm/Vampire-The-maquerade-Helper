package co.com.lkm.shen.vampirehelper.DataSource;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Dao.ChronicleDao;
import co.com.lkm.shen.vampirehelper.Repository.ChronicleRepository;

public class ChronicleDataSource implements ChronicleRepository {

    private ChronicleDao mDhronicleDao;

    @Inject
    public ChronicleDataSource(ChronicleDao chronicleDao){
        mDhronicleDao = chronicleDao;
    }

    @Override
    public LiveData<List<Chronicle>> getAllChronicles(){
        return mDhronicleDao.getAllChronicles();
    }

    @Override
    public void insert (Chronicle chronicle) {
        new insertAsyncTask(mDhronicleDao).execute(chronicle);
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

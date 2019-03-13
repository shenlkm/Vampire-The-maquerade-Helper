package co.com.lkm.shen.vampirehelper.Data.DataSource;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Repository.ChronicleRepository;
import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.ChronicleDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;

public class ChronicleDataSource implements ChronicleRepository {

    private ChronicleDao mChronicleDao;

    @Inject
    public ChronicleDataSource(ChronicleDao chronicleDao){
        mChronicleDao = chronicleDao;
    }

    @Override
    public LiveData<List<Chronicle>> getAllChronicles(){
        return mChronicleDao.getAllChronicles();
    }

    @Override
    public void insert (Chronicle...chronicle) {
        new insertAsyncTask(mChronicleDao).execute(chronicle);
    }

    @Override
    public void update(Chronicle... items) {
        mChronicleDao.update(items);
    }

    @Override
    public void delete(Chronicle... items) {
        mChronicleDao.delete(items);
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

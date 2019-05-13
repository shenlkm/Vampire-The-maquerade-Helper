package co.com.lkm.shen.vampirehelper.Data.DataSource;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.SceneDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.Data.Repository.SceneRepository;

public class SceneDataSource implements SceneRepository {

    private SceneDao mSceneDao;

    @Inject
    public SceneDataSource(SceneDao sceneDao){
        mSceneDao = sceneDao;
    }

    @Override
    public LiveData<List<Scene>> getChronicleScenes(Long mChronicleID) {
        return mSceneDao.getChronicleScenes(mChronicleID);
    }

    @Override
    public LiveData<Scene> getSceneById(int mId) {
        return mSceneDao.getSceneById(mId);
    }

    @Override
    public void insert (Scene...scenes) {
        new SceneDataSource.insertAsyncTask(mSceneDao).execute(scenes);
    }

    @Override
    public void update(Scene... items) {
        mSceneDao.update(items);
    }

    @Override
    public void delete(Scene... items) {
        mSceneDao.delete(items);
    }

    private static class insertAsyncTask extends AsyncTask<Scene, Void, Void> {

        private SceneDao mAsyncTaskDao;

        insertAsyncTask(SceneDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Scene... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

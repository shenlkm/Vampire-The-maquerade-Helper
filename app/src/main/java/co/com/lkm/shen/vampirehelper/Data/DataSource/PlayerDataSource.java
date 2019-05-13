package co.com.lkm.shen.vampirehelper.Data.DataSource;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.PlayerDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Repository.PlayerRepository;

public class PlayerDataSource implements PlayerRepository {

    private PlayerDao mPlayerDao;

    @Inject
    public PlayerDataSource(PlayerDao playerDao){
        mPlayerDao = playerDao;
    }


    @Override
    public LiveData<List<Player>> getChroniclePlayers(Long mChronicleID) {
        return mPlayerDao.getChroniclePlayer(mChronicleID);
    }

    @Override
    public void insert(Player... items) {
        new PlayerDataSource.insertAsyncTask(mPlayerDao).execute(items);
    }

    @Override
    public void update(Player... items) {
        mPlayerDao.update(items);
    }

    @Override
    public void delete(Player... items) {
        mPlayerDao.delete(items);
    }

    private static class insertAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao mAsyncTaskDao;

        insertAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

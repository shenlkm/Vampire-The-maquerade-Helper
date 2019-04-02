package co.com.lkm.shen.vampirehelper.Data.DataSource;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Dao.BattleDao;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Repository.BattleRepository;

public class BattleDataSource implements BattleRepository {

    private BattleDao mBattleDao;

    @Inject
    public BattleDataSource(BattleDao playerDao){
        mBattleDao = playerDao;
    }


    @Override
    public LiveData<List<Player>> getAllPlayersInBattle(Long sceneId) {
        return mBattleDao.getAllPlayersInBattle(sceneId);
    }

    @Override
    public void insert(Battle... items) {
        new insertAsyncTask(mBattleDao).execute(items);
    }

    @Override
    public void update(Battle... items) {
        mBattleDao.update(items);
    }

    @Override
    public void delete(Battle... items) {
        mBattleDao.delete(items);
    }

    private static class insertAsyncTask extends AsyncTask<Battle, Void, Void> {

        private BattleDao mAsyncTaskDao;

        insertAsyncTask(BattleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Battle... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

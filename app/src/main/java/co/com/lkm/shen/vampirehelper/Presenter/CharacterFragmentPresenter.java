package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Player;
import io.realm.Sort;

public class CharacterFragmentPresenter extends BaseRecyclerPresenter<Player> {

    public CharacterFragmentPresenter(BaseView view, long id) {
        super(view, id);
    }
    @Override
    public void loadMyList(long id) {
        myList = realm.
                where(Chronicle.class)
                .equalTo("id", id)
                .findFirst()
                .getPlayers().sort("initiative", Sort.DESCENDING);
    }

}

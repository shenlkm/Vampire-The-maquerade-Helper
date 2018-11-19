package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Player;
import io.realm.OrderedRealmCollection;

public class CharacterFragmentPresenter extends BasePresenter {

    private OrderedRealmCollection<Player> characters;

    public CharacterFragmentPresenter(BaseView view, long id) {
        super(view);
        prepareRealm();
        loadCharacteres(id);
    }

    private void loadCharacteres(long id) {
        characters = realm.
                where(Chronicle.class)
                .equalTo("id", id)
                .findFirst()
                .getPlayers();
    }

    public OrderedRealmCollection<Player> getCharacters() {
        return characters;
    }
}

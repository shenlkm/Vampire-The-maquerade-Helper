package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Player;
import io.realm.Realm;

public class CreateCharacterActivityPresenter extends BasePresenter{

    public CreateCharacterActivityPresenter(BaseView view) {
        super(view);
        prepareRealm();
    }

    public int getResource(int i) {
        return Constants.CLAN_LOGOS[i];
    }

    public void saveChatacter(final String playerName, final String characterName, final String initiative, final int clan, final long id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Chronicle current = realm.where(Chronicle.class)
                        .equalTo("id", id)
                        .findFirst();
                if(current != null){
                    Player player =realm.createObject(Player.class);
                    player.setName(playerName);
                    player.setCharacterName(characterName);
                    player.setInitiative(Integer.parseInt(initiative));
                    player.setClan(clan);
                    current.getPlayers().add(player);
                    realm.insertOrUpdate(current);
                }
            }
        });
    }
}
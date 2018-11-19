package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Player;
import co.com.lkm.shen.vampirehelper.R;
import io.realm.Realm;

public class CreateCharacterActivityPresenter extends BasePresenter{

    private static final int[] CLAN_LOGOS =
            new int[]{
                R.drawable.ic_logo_clan_brujah,
                R.drawable.ic_logo_clan_gangrel,
                R.drawable.ic_logo_clan_malkavian,
                R.drawable.ic_logo_clan_nosferatu,
                R.drawable.ic_logo_clan_toreador,
                R.drawable.ic_logo_clan_tremere,
                R.drawable.ic_logo_clan_ventrue,
                R.drawable.ic_logo_clan_lasombra,
                R.drawable.ic_logo_clan_tzimice,
                R.drawable.ic_logo_clan_assamite,
                R.drawable.ic_logo_clan_giovanny,
                R.drawable.ic_logo_clan_setfollowers,
                R.drawable.ic_logo_clan_ravnos };

    public CreateCharacterActivityPresenter(BaseView view) {
        super(view);
        prepareRealm();
    }

    public int getResource(int i) {
        return CLAN_LOGOS[i];
    }

    public void saveChatacter(final String playerName, final String characterName, final String initiative, final String clan, final long id) {
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
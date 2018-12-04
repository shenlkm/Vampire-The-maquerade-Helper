package co.com.lkm.shen.vampirehelper.Presenter;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import co.com.lkm.shen.vampirehelper.Contracts.Views.ChronicleView;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.Domain.Scene;
import io.realm.Realm;

public class ChronicleActivityPresenter extends BasePresenter {

    public ChronicleActivityPresenter(ChronicleView view) {
        super(view);
        prepareRealm();
    }

    public boolean addScene(final String sceneName, final long id) {
        if("".equals(sceneName))
            return false;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Chronicle current = realm.where(Chronicle.class)
                        .equalTo("id", id)
                        .findFirst();
                if(current != null){
                    Scene scene =realm.createObject(Scene.class, UUID.randomUUID().getMostSignificantBits());
                    scene.setName(sceneName);
                    scene.setStartDate(Calendar.getInstance().getTime());
                    current.getScenes().add(scene);
                    realm.insertOrUpdate(current);
                }
            }
        });
        return  true;
    }
}

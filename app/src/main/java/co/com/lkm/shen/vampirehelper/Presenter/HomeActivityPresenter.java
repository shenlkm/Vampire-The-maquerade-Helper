package co.com.lkm.shen.vampirehelper.Presenter;

import android.widget.Toast;

import java.util.UUID;

import co.com.lkm.shen.vampirehelper.Contracts.Views.HomeView;
import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.exceptions.RealmException;

public class HomeActivityPresenter extends BasePresenter{

    private HomeView mView;
    private Realm realm;
    private OrderedRealmCollection<Chronicle> chronicles;

    public  HomeActivityPresenter(HomeView view){
        super(view);
        mView = view;
        realm = Realm.getDefaultInstance();
        loadaChronicles();
    }

    public OrderedRealmCollection<Chronicle> getChronicles(){
        return chronicles;
    }

    private void loadaChronicles(){
        chronicles = realm.where(Chronicle.class).findAll();
    }

    public void showList() {
        this.mView.showList(chronicles);
    }

    public boolean addChronicle(final String name){
        if("".equals(name))
            return false;
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Chronicle results = realm.where(Chronicle.class)
                            .equalTo("name", name)
                            .findFirst();
                    if(results == null)
                    {
                        results = realm.createObject(Chronicle.class, UUID.randomUUID().getMostSignificantBits());
                        results.setName(name);
                        realm.insertOrUpdate(results);
                    }
                }
            });
            return  true;
        } catch (RealmException e) {
            return  false;
        }
    }

    public void onDistroy(){
        realm.close();
    }
}

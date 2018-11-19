package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.RealmManager;
import io.realm.Realm;

public class BasePresenter {

    protected BaseView mView;
    protected Realm realm;

    public BasePresenter(BaseView view){
        mView = view;
    }

    public void prepareRealm(){
        RealmManager.incrementCount();
        realm = RealmManager.getRealm();
    }

    public void onDistroy(){
        RealmManager.decrementCount();
    }

    public void setupView(){
        mView.setupView();
    }
}

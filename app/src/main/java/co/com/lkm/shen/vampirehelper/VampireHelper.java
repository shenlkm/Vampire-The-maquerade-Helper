package co.com.lkm.shen.vampirehelper;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class VampireHelper extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmManager.initializeRealmConfig(this);
    }
}

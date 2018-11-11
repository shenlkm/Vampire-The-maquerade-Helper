package co.com.lkm.shen.vampirehelper;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class VampireHelper extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("vampireHelper.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}

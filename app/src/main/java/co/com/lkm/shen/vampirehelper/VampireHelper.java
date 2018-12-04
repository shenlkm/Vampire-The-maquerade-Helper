package co.com.lkm.shen.vampirehelper;

import android.app.Application;

public class VampireHelper extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmManager.initializeRealmConfig(this);
    }
}

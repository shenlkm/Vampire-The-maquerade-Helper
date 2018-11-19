package co.com.lkm.shen.vampirehelper;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {

    static Realm realm;
    final static  String CONFIG_NAME = "vampireHelper.realm";
    final static int SCHEMA_VERSION = 1;

    static RealmConfiguration realmConfiguration;

    public static void initializeRealmConfig(Context appContext) {
        if(realmConfiguration == null) {
            Realm.init(appContext);
            setRealmConfiguration( new RealmConfiguration.Builder()
                    .name("vampireHelper.realm")
                    .schemaVersion(1)
                    .build());
        }
    }

    public static void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        RealmManager.realmConfiguration = realmConfiguration;
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private static int activityCount = 0;

    public static Realm getRealm() {
        return realm;
    }

    public static void incrementCount() {
        if(activityCount == 0) {
            if(realm != null) {
                if(!realm.isClosed()) {
                    realm.close();
                }
            }
            realm = Realm.getDefaultInstance();
        }
        activityCount++;
    }

    public static void decrementCount() {
        activityCount--;
        if(activityCount <= 0) {
            activityCount = 0;
            realm.close();
            Realm.compactRealm(realmConfiguration);
            realm = null;
        }
    }
}

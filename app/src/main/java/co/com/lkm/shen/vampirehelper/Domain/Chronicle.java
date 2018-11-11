package co.com.lkm.shen.vampirehelper.Domain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chronicle extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private RealmList<Scene> scenes;
    private RealmList<Player> players;

    public RealmList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(RealmList<Player> players) {
        this.players = players;
    }

    public RealmList<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(RealmList<Scene> scenes) {
        this.scenes = scenes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

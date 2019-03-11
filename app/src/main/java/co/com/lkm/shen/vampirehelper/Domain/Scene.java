package co.com.lkm.shen.vampirehelper.Domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "scene")
public class Scene{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "start_date")
    private Date startDate;
    @ColumnInfo(name = "end_date")
    private Date endtDate;

    @ColumnInfo(name = "chronicle_id")
    private int ChronicleId;
    /*private RealmList<Player> fighters;

    public RealmList<Player> getFighters() {
        return fighters;
    }

    public void setFighters(RealmList<Player> fighters) {
        this.fighters = fighters;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndtDate() {
        return endtDate;
    }

    public void setEndtDate(Date endtDate) {
        this.endtDate = endtDate;
    }
    public int getChronicleId() {
        return ChronicleId;
    }

    public void setChronicleId(int chronicleId) {
        ChronicleId = chronicleId;
    }
}

package co.com.lkm.shen.vampirehelper.Domain;

import java.util.Date;

public class Scene{

    private long id;
    private String name;
    private Date startDate;
    private Date endtDate;
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
}

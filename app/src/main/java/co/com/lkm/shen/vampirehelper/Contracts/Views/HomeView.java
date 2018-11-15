package co.com.lkm.shen.vampirehelper.Contracts.Views;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import io.realm.OrderedRealmCollection;

public interface HomeView extends BaseView{
    void showList(OrderedRealmCollection<Chronicle> chronicles);
}

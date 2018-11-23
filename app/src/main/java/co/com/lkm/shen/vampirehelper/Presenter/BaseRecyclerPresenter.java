package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import io.realm.OrderedRealmCollection;

public abstract class BaseRecyclerPresenter<T> extends BasePresenter {

    protected OrderedRealmCollection<T> myList;

    public BaseRecyclerPresenter(BaseView view, long id) {
        super(view);
        prepareRealm();
        loadMyList(id);
    }

    public abstract void loadMyList(long id);

    public OrderedRealmCollection<T> getMyList() {
        return myList;
    }
}

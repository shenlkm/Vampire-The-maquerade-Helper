package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Domain.Scene;
import io.realm.OrderedRealmCollection;

public class SceneFragmentPresenter extends BasePresenter {

    public SceneFragmentPresenter(BaseView view, long id) {
        super(view);
    }

    public OrderedRealmCollection<Scene> getScenes() {
        return null;
    }
}

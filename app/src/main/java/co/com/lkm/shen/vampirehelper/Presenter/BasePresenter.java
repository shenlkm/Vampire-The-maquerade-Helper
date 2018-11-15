package co.com.lkm.shen.vampirehelper.Presenter;

import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;

public class BasePresenter {

    private BaseView mView;

    public BasePresenter(BaseView view){
        mView = view;
    }

    public void setupView(){
        mView.setupView();
    }
}

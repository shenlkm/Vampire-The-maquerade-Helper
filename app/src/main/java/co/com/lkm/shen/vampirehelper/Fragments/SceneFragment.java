package co.com.lkm.shen.vampirehelper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Adapters.SceneAdapter;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;


public class SceneFragment extends BaseRecyclerFragment{

    private SceneAdapter mSceneAdapter;

    public SceneFragment(){
    }

    public static SceneFragment newInstance(long id) {
        SceneFragment fragment = new SceneFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.CHRONICLE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        this.setupView();
        return rootView;
    }

    @Override
    public void setupView() {
        super.setupView();
        /*mSceneAdapter = new SceneAdapter(getActivity(), mPresenter.getMyList());
        mRecyclerView.setAdapter(mSceneAdapter);*/
    }
}

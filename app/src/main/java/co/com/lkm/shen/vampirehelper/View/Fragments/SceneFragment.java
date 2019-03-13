package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.View.Adapters.SceneAdapter;
import co.com.lkm.shen.vampirehelper.ViewModel.ChronicleViewModel;


public class SceneFragment extends BaseRecyclerFragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public ChronicleViewModel mChronicleViewModel;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChronicleViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ChronicleViewModel.class);

        mChronicleViewModel.getChronicleScenes(mId).observe(this, new Observer<List<Scene>>() {
            @Override
            public void onChanged(@Nullable List<Scene> chronicles) {
                mSceneAdapter.setScenes(chronicles);
            }
        });
    }

    @Override
    public void setupView() {
        super.setupView();
        mSceneAdapter = new SceneAdapter(getActivity());
        mRecyclerView.setAdapter(mSceneAdapter);
    }
}

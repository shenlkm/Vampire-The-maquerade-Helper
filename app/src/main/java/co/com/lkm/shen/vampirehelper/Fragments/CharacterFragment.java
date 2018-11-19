package co.com.lkm.shen.vampirehelper.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Adapters.CharacterAdapter;
import co.com.lkm.shen.vampirehelper.Adapters.ChronicleAdapter;
import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Presenter.CharacterFragmentPresenter;
import co.com.lkm.shen.vampirehelper.R;


public class CharacterFragment extends Fragment implements BaseView {

    @BindView(R.id.characters) RecyclerView mRecyclerView;

    private final static String KEY_ID_EXTRAS = "ID";

    private RecyclerView.LayoutManager mLayoutManager;
    private CharacterAdapter mCharacterAdapter;
    private CharacterFragmentPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        Bundle extras = getActivity().getIntent().getExtras();
        long id = extras.getLong(KEY_ID_EXTRAS);
        mPresenter = new CharacterFragmentPresenter(this, id);
        mPresenter.setupView();
        return rootView;
    }

    @Override
    public void setupView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCharacterAdapter = new CharacterAdapter(getActivity(), mPresenter.getCharacters());
        mRecyclerView.setAdapter(mCharacterAdapter);
    }
}

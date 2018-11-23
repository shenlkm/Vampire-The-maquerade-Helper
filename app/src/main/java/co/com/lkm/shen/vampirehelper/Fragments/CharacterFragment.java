package co.com.lkm.shen.vampirehelper.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Adapters.CharacterAdapter;
import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.Presenter.CharacterFragmentPresenter;
import co.com.lkm.shen.vampirehelper.R;


public class CharacterFragment extends BaseRecyclerFragment {

    private CharacterAdapter mCharacterAdapter;
    private CharacterFragmentPresenter mPresenter;

    public CharacterFragment(){
    }

    public static CharacterFragment newInstance(long id) {
        CharacterFragment fragment = new CharacterFragment();
        Bundle args = new Bundle();
        args.putLong(CHRONICLE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        mPresenter = new CharacterFragmentPresenter(this, mId);
        mPresenter.setupView();
        return rootView;
    }

    @Override
    public void setupView() {
        super.setupView();
        mCharacterAdapter = new CharacterAdapter(getActivity(), mPresenter.getMyList());
        mRecyclerView.setAdapter(mCharacterAdapter);
    }
}

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
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.View.Adapters.CharacterAdapter;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.ViewModel.ChronicleViewModel;


public class CharacterFragment extends BaseRecyclerFragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public ChronicleViewModel mChronicleViewModel;

    private CharacterAdapter mCharacterAdapter;

    public CharacterFragment(){
    }

    public static CharacterFragment newInstance(long id) {
        CharacterFragment fragment = new CharacterFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.CHRONICLE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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

        mChronicleViewModel.getChroniclePlayers(mId).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                mCharacterAdapter.setPlayers(players);
            }
        });
    }

    @Override
    public void setupView() {
        super.setupView();
        mCharacterAdapter = new CharacterAdapter(getActivity());
        mRecyclerView.setAdapter(mCharacterAdapter);
    }
}

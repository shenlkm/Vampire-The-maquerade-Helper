package co.com.lkm.shen.vampirehelper.View.Fragments;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.CharacterAdapter;
import co.com.lkm.shen.vampirehelper.View.HomeActivity;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChronicleViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ChronicleViewModel.class);

        mChronicleViewModel.getChroniclePlayers(chronicle_id).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                mCharacterAdapter.setPlayers(players);
            }
        });
    }

    @Override
    public void setupView() {
        super.setupView();
        create = getActivity().findViewById(R.id.create_character_button);
        create.setOnClickListener(this::createCharacter);
        mCharacterAdapter = new CharacterAdapter(getActivity());
        binding.frameList.setAdapter(mCharacterAdapter);
    }

    public void createCharacter(View v){
        HideButtons();
        CreateCharacterFragment fragment = CreateCharacterFragment.newInstance(chronicle_id);
        ((HomeActivity) getActivity()).replaceFragment(fragment);
    }
}

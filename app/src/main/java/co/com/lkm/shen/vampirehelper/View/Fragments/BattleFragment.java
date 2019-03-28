package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.ViewModel.BattleViewModel;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class BattleFragment extends Fragment implements Injectable {

    @BindView(R.id.battle_floating_button)
    FloatingActionButton addParticipant;

    public BattleViewModel mBattleViewModel;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public static BattleFragment newInstance(Long id) {
        BattleFragment fragment = new BattleFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.CHRONICLE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_battle, container, false);
        ButterKnife.bind(this, rootView);
        setupView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBattleViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(BattleViewModel.class);
    }

    private void setupView() {
        addParticipant.setOnClickListener(v -> addNewParticipant(v));
    }

    private void addNewParticipant(View v) {
    }
}

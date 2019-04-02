package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Battle;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.BattleAdapter;
import co.com.lkm.shen.vampirehelper.ViewModel.BattleViewModel;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class BattleFragment extends Fragment implements Injectable {

    @BindView(R.id.frameList) public RecyclerView mRecyclerView;
    @BindView(R.id.select_party) public ConstraintLayout buttonDone;

    public BattleViewModel mBattleViewModel;
    private BattleAdapter mBattleAdapter;
    private Long chronicle_id;
    private Long scene_id;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public static BattleFragment newInstance(Long chronicle_id, Long scene_id) {
        BattleFragment fragment = new BattleFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.CHRONICLE_ID, chronicle_id);
        args.putLong(Constants.SCENE_ID, scene_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chronicle_id = getArguments().getLong(Constants.CHRONICLE_ID);
            scene_id = getArguments().getLong(Constants.SCENE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_battle, container, false);
        ButterKnife.bind(this, rootView);
        setupView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBattleViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(BattleViewModel.class);

        mBattleViewModel.getChroniclePlayers(chronicle_id).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                if(!mBattleAdapter.isPartyCreated())
                    mBattleAdapter.setPlayers(players);
            }
        });

        mBattleViewModel.getChroniclePlayersOnBattle(scene_id).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> players) {
                if(mBattleAdapter.isPartyCreated())
                    mBattleAdapter.setPlayers(players);
            }
        });
    }

    private void setupView() {

        mBattleAdapter = new BattleAdapter(getActivity(), false);

        mRecyclerView.setAdapter(mBattleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        buttonDone.setOnClickListener(v -> selectParty(v));
    }

    private void selectParty(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure these are all the participants?");
        builder.setPositiveButton("YES", (dialog, which) -> onConfirm(dialog));
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
        builder.show();

    }

    private void onConfirm(DialogInterface dialog) {
        List<Player> players = mBattleAdapter.createParty();
        for (Player player : players) {
            mBattleViewModel.insert(new Battle(scene_id, player.getId()));
        }
    }
}

package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.View.Adapters.SceneAdapter;
import co.com.lkm.shen.vampirehelper.ViewModel.ChronicleViewModel;


public class SceneFragment extends BaseRecyclerFragment {

    public FloatingActionButton create;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public ChronicleViewModel mChronicleViewModel;
    private SceneAdapter mSceneAdapter;
    private Long chronicle_id;

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

        mChronicleViewModel.setChronicle_id(chronicle_id);

        mChronicleViewModel.getChronicleScenes().observe(this, new Observer<List<Scene>>() {
            @Override
            public void onChanged(@Nullable List<Scene> scenes) {
                mSceneAdapter.setScenes(scenes);
            }
        });
    }

    @Override
    public void setupView() {
        super.setupView();
        mSceneAdapter = new SceneAdapter(getActivity());
        mRecyclerView.setAdapter(mSceneAdapter);
        create = (FloatingActionButton) getActivity().findViewById(R.id.createPlayer);
        create.setOnClickListener((v) -> createScene(v));
    }

    public void createScene(View v){

        DashboardFragment dashboardFragment = (DashboardFragment)  getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        dashboardFragment.showButtons(false);

        AlertDialog.Builder builder = getInputDialog();
        builder.show();
    }

    private AlertDialog.Builder getInputDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Scene");

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Scene scene= new Scene(input.getText().toString(), chronicle_id);
                mChronicleViewModel.insert(scene);
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder;
    }
}

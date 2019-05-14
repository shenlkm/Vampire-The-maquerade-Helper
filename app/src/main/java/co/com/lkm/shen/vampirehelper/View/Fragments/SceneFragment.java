package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.R;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChronicleViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ChronicleViewModel.class);

        mChronicleViewModel.getChronicleScenes(chronicle_id).observe(this, scenes -> mSceneAdapter.setScenes(scenes));
    }

    @Override
    public void setupView() {
        super.setupView();
        mSceneAdapter = new SceneAdapter(getActivity());
        binding.frameList.setAdapter(mSceneAdapter);
        create = getActivity().findViewById(R.id.create_scene_button);
        create.setOnClickListener(this::createScene);
    }

    public void createScene(View v){
        HideButtons();
        AlertDialog.Builder builder = getInputDialog();
        builder.show();
    }

    private AlertDialog.Builder getInputDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Scene");

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            Scene scene= new Scene(input.getText().toString(), chronicle_id);
            mChronicleViewModel.insert(scene);
            dialogInterface.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        return builder;
    }
}
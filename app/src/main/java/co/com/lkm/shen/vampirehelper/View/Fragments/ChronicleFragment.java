package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;
import co.com.lkm.shen.vampirehelper.View.Adapters.ChronicleAdapter;
import co.com.lkm.shen.vampirehelper.ViewModel.HomeViewModel;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class ChronicleFragment  extends Fragment implements Injectable {

    public static final String TAG = "ChronicleFragment";

    @BindView(R.id.frameList) public RecyclerView mRecyclerView;
    @BindView(R.id.createChronicle) public FloatingActionButton create;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private ChronicleAdapter mChronicleAdapter;
    public HomeViewModel mHomeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.app_bar_home, container, false);
        ButterKnife.bind(this, rootView);
        setupView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHomeViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(HomeViewModel.class);

        mHomeViewModel.getChronicles().observe(this, new Observer<List<Chronicle>>() {
            @Override
            public void onChanged(@Nullable List<Chronicle> chronicles) {
                mChronicleAdapter.setChronicles(chronicles);
            }
        });
    }

    public void setupView() {

        mChronicleAdapter = new ChronicleAdapter(getActivity());
        mRecyclerView.setAdapter(mChronicleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        create.setOnClickListener( (v) -> createChronicle(v));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
    }

    public  void createChronicle(View view)
    {
        AlertDialog.Builder builder = getInputDialog();
        builder.show();
    }

    private AlertDialog.Builder getInputDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.title_dialog_create_chronicles));

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Chronicle chronicle = new Chronicle(input.getText().toString());
                mHomeViewModel.insert(chronicle);
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

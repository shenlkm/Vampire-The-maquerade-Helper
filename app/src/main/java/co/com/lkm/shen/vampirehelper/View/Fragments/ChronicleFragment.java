package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.ChronicleAdapter;
import co.com.lkm.shen.vampirehelper.ViewModel.HomeViewModel;
import co.com.lkm.shen.vampirehelper.databinding.AppBarHomeBinding;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class ChronicleFragment  extends Fragment implements Injectable {

    public static final String TAG = "ChronicleFragment";

    private AppBarHomeBinding binding;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private ChronicleAdapter mChronicleAdapter;
    public HomeViewModel mHomeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = AppBarHomeBinding.inflate(inflater,container,  false);
        setupView();
        return binding.getRoot();
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
        binding.contentLayout.frameList.setAdapter(mChronicleAdapter);
        binding.contentLayout.frameList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.createChronicle.setOnClickListener(this::createChronicle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.contentLayout.frameList.setAdapter(null);
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

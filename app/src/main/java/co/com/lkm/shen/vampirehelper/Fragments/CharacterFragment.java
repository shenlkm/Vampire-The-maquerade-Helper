package co.com.lkm.shen.vampirehelper.Fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Contracts.NoticeDialogListener;
import co.com.lkm.shen.vampirehelper.R;


public class CharacterFragment extends Fragment implements NoticeDialogListener {

    @BindView(R.id.characters) RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(getActivity(), "Hello", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getActivity(), "Hello Close", Toast.LENGTH_LONG).show();
    }
}

package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.databinding.FragmentItemListBinding;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public abstract class BaseRecyclerFragment extends Fragment implements Injectable {

    public Long chronicle_id;
    public FloatingActionButton create;
    protected FragmentItemListBinding binding;

    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chronicle_id = getArguments().getLong(Constants.CHRONICLE_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemListBinding.inflate(inflater, container, false);
        this.setupView();
        return binding.getRoot();
    }

    public void setupView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        binding.frameList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.frameList.setAdapter(null);
    }

    protected void HideButtons() {
        DashboardFragment dashboardFragment = (DashboardFragment)  getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        dashboardFragment.showButtons(false);
    }
}

package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public abstract class BaseRecyclerFragment extends Fragment implements Injectable {

    @BindView(R.id.frameList)
    public RecyclerView mRecyclerView;
    public Long chronicle_id;

    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chronicle_id = getArguments().getLong(Constants.CHRONICLE_ID);
        }
    }

    public void setupView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
    }

    protected void HideButtons() {
        DashboardFragment dashboardFragment = (DashboardFragment)  getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        dashboardFragment.showButtons(false);
    }
}

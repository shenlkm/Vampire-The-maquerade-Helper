package co.com.lkm.shen.vampirehelper.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import co.com.lkm.shen.vampirehelper.Contracts.Views.BaseView;
import co.com.lkm.shen.vampirehelper.R;

public abstract class BaseRecyclerFragment extends Fragment implements BaseView {

    @BindView(R.id.frameList)
    public RecyclerView mRecyclerView;
    public Long mId;

    protected RecyclerView.LayoutManager mLayoutManager;

    protected static final String CHRONICLE_ID = "CHRONICLE_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getLong(CHRONICLE_ID);
        }
    }

    @Override
    public void setupView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}

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
    public Long mId;

    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getLong(Constants.CHRONICLE_ID);
        }
    }

    public void setupView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
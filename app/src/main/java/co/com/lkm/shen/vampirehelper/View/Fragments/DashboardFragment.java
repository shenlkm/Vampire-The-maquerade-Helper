package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.ChroniclePageAdapter;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class DashboardFragment extends Fragment implements Injectable {

    @BindView(R.id.chronicleFloatingMenu) public FloatingActionButton menuButton;
    @BindView(R.id.chronicle_pager) public ViewPager chroniclePager;
    @BindView(R.id.sceneLayout) public LinearLayout sceneLayout;
    @BindView(R.id.characterLayout) public LinearLayout characterLayout;

    private static Long chronicle_id;

    public ChroniclePageAdapter mChroniclePageAdapter;

    public static DashboardFragment newInstance(long id) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.CHRONICLE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chronicle_id = getArguments().getLong(Constants.CHRONICLE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chronicle, container, false);
        ButterKnife.bind(this, rootView);
        this.setupView();
        return rootView;
    }

    public void setupView() {

        menuButton.setOnClickListener((v) -> showOptions(v));
        mChroniclePageAdapter = new ChroniclePageAdapter(getActivity().getSupportFragmentManager(), chronicle_id);
        chroniclePager.setAdapter(mChroniclePageAdapter);
    }

    public void showButtons(boolean show) {
        if(show){
            animateButton(AnimationUtils
                            .loadAnimation(getActivity(), R.anim.show_button),
                    AnimationUtils.loadAnimation(getContext(), R.anim.show_layout), View.VISIBLE);
        } else{
            animateButton(AnimationUtils
                            .loadAnimation(getActivity(), R.anim.hide_button),
                    AnimationUtils.loadAnimation(getContext(), R.anim.hide_layout), View.GONE);
        }
    }

    public void showOptions(View v) {
        this.showButtons(sceneLayout.getVisibility() == View.GONE && characterLayout.getVisibility() == View.GONE);
    }

    private void animateButton(Animation showBottons, Animation showLayouts, int visible) {
        sceneLayout.setVisibility(visible);
        characterLayout.setVisibility(visible);

        menuButton.startAnimation(showBottons);
        sceneLayout.startAnimation(showLayouts);
        characterLayout.startAnimation(showLayouts);
    }
}
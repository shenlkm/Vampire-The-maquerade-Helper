package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.ChroniclePageAdapter;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class DashboardFragment extends Fragment implements Injectable {

    public FloatingActionButton menuButton;
    public ViewPager chroniclePager;
    public LinearLayout sceneLayout;
    public LinearLayout characterLayout;

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
    public void onResume() {
        super.onResume();
        showButtons(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chronicle, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setupView();
    }

    public void setupView() {

        menuButton.setOnClickListener((v) -> showOptions(v));
        mChroniclePageAdapter = new ChroniclePageAdapter(getChildFragmentManager(), chronicle_id);
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
package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.ChroniclePageAdapter;
import co.com.lkm.shen.vampirehelper.databinding.FragmentChronicleBinding;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class DashboardFragment extends Fragment implements Injectable {

    private FragmentChronicleBinding binding;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChronicleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setupView();
    }

    public void setupView() {

        binding.chronicleFloatingMenu.setOnClickListener(this::showOptions);
        mChroniclePageAdapter = new ChroniclePageAdapter(getChildFragmentManager(), chronicle_id);
        binding.chroniclePager.setAdapter(mChroniclePageAdapter);
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
        this.showButtons(binding.sceneLayoutMenuContainer.getVisibility() == View.GONE && binding.characterLayoutMenuContainer.getVisibility() == View.GONE);
    }

    private void animateButton(Animation showBottons, Animation showLayouts, int visible) {
        binding.sceneLayoutMenuContainer.setVisibility(visible);
        binding.characterLayoutMenuContainer.setVisibility(visible);

        binding.chronicleFloatingMenu.startAnimation(showBottons);
        binding.sceneLayoutMenuContainer.startAnimation(showLayouts);
        binding.characterLayoutMenuContainer.startAnimation(showLayouts);
    }
}
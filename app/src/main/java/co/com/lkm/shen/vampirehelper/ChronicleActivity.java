package co.com.lkm.shen.vampirehelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Adapters.ChroniclePageAdapter;
import co.com.lkm.shen.vampirehelper.Contracts.NoticeDialogListener;
import co.com.lkm.shen.vampirehelper.Contracts.Views.ChronicleView;
import co.com.lkm.shen.vampirehelper.Fragments.CreateCharacterDialogFragment;
import co.com.lkm.shen.vampirehelper.Presenter.ChronicleActivityPresenter;

public class ChronicleActivity extends AppCompatActivity implements ChronicleView {

    @BindView(R.id.toolbar) public Toolbar mToolbar;
    @BindView(R.id.chronicleFloatingMenu) public FloatingActionButton menuButton;
    @BindView(R.id.chronicle_pager) public ViewPager chroniclePager;
    @BindView(R.id.sceneLayout) public LinearLayout sceneLayout;
    @BindView(R.id.characterLayout) public LinearLayout characterLayout;

    public ChroniclePageAdapter mChroniclePageAdapter;
    private ChronicleActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronicle);
        ButterKnife.bind(this);

        mPresenter = new ChronicleActivityPresenter(this);
        mPresenter.setupView();
    }

    public void showOptions(View v) {
        if(sceneLayout.getVisibility() == View.GONE && characterLayout.getVisibility() == View.GONE){
            animateButton(AnimationUtils
                    .loadAnimation(this, R.anim.show_button),
                    AnimationUtils.loadAnimation(this, R.anim.show_layout), View.VISIBLE);
        } else {
            animateButton(AnimationUtils
                    .loadAnimation(this, R.anim.hide_button),
                    AnimationUtils.loadAnimation(this, R.anim.hide_layout), View.GONE);
        }
    }

    @Override
    public void animateButton(Animation showBottons, Animation showLayouts, int visible) {
        sceneLayout.setVisibility(visible);
        characterLayout.setVisibility(visible);

        menuButton.startAnimation(showBottons);
        sceneLayout.startAnimation(showLayouts);
        characterLayout.startAnimation(showLayouts);
    }

    public void createCharacter(View v){
        CreateCharacterDialogFragment dialogFragment = new CreateCharacterDialogFragment();
        dialogFragment.setListener((NoticeDialogListener) this.getFragmentManager().findFragmentById(R.id.fragment_dialog_create_character));
        dialogFragment.show(getFragmentManager(), "tag");
    }

    @Override
    public void setupView() {
        setSupportActionBar(mToolbar);
        mChroniclePageAdapter = new ChroniclePageAdapter(getSupportFragmentManager());
        chroniclePager.setAdapter(mChroniclePageAdapter);
    }
}

package co.com.lkm.shen.vampirehelper.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Adapters.ChroniclePageAdapter;
import co.com.lkm.shen.vampirehelper.View.Fragments.SceneFragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ChronicleActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @BindView(R.id.toolbar) public Toolbar mToolbar;
    @BindView(R.id.chronicleFloatingMenu) public FloatingActionButton menuButton;
    @BindView(R.id.chronicle_pager) public ViewPager chroniclePager;
    @BindView(R.id.sceneLayout) public LinearLayout sceneLayout;
    @BindView(R.id.characterLayout) public LinearLayout characterLayout;

    public ChroniclePageAdapter mChroniclePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronicle);
        ButterKnife.bind(this);

        this.setupView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.showButtons(false);
    }

    public void showButtons(boolean show) {
        if(show){
            animateButton(AnimationUtils
                            .loadAnimation(this, R.anim.show_button),
                    AnimationUtils.loadAnimation(this, R.anim.show_layout), View.VISIBLE);
        } else{
            animateButton(AnimationUtils
                            .loadAnimation(this, R.anim.hide_button),
                    AnimationUtils.loadAnimation(this, R.anim.hide_layout), View.GONE);
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

    public void createCharacter(View v){
        Intent intent = new Intent(this, CreateCharacterActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }

    public void setupView() {
        setSupportActionBar(mToolbar);
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong(Constants.KEY_ID_EXTRAS);
        setTitle(extras.getString(Constants.KEY_TITLE_EXTRAS));
        mChroniclePageAdapter = new ChroniclePageAdapter(getSupportFragmentManager(), id);
        chroniclePager.setAdapter(mChroniclePageAdapter);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
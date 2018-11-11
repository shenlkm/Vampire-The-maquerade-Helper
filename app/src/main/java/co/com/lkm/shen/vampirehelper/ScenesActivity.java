package co.com.lkm.shen.vampirehelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScenesActivity extends AppCompatActivity {

    @BindView(R.id.chronicleFloatingMenu) public FloatingActionButton menuButton;
    @BindView(R.id.sceneLayout) public LinearLayout sceneLayout;
    @BindView(R.id.characterLayout) public LinearLayout characterLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    public void showOptions(View v) {
        Animation showBottons = AnimationUtils.loadAnimation(this, R.anim.show_button);
        Animation showLayouts = AnimationUtils.loadAnimation(this, R.anim.show_layout);

        Animation hideButtons = AnimationUtils.loadAnimation(this, R.anim.hide_button);
        Animation hideLayouts = AnimationUtils.loadAnimation(this, R.anim.hide_layout);


        if(sceneLayout.getVisibility() == View.GONE && characterLayout.getVisibility() == View.GONE){
            sceneLayout.setVisibility(View.VISIBLE);
            characterLayout.setVisibility(View.VISIBLE);

            menuButton.startAnimation(showBottons);
            sceneLayout.startAnimation(showLayouts);
            characterLayout.startAnimation(showLayouts);
        } else {
            sceneLayout.setVisibility(View.GONE);
            characterLayout.setVisibility(View.GONE);

            menuButton.startAnimation(hideButtons);
            sceneLayout.startAnimation(hideLayouts);
            characterLayout.startAnimation(hideLayouts);
        }
    }

    public void createCharacter(View v){
        
    }

}

package co.com.lkm.shen.vampirehelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Adapters.ChroniclePageAdapter;
import co.com.lkm.shen.vampirehelper.Contracts.Views.ChronicleView;
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

    @Override
    protected void onPause() {
        super.onPause();
        animateButton(AnimationUtils
                        .loadAnimation(this, R.anim.hide_button),
                AnimationUtils.loadAnimation(this, R.anim.hide_layout), View.GONE);
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
        Intent intent = new Intent(this, CreateCharacterActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }

    public void createScene(View v){
        AlertDialog.Builder builder = getInputDialog();
        builder.show();
    }

    private AlertDialog.Builder getInputDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Scene");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle extras = getIntent().getExtras();
                long id = extras.getLong(Constants.KEY_ID_EXTRAS);
                if(mPresenter.addScene(input.getText().toString(), id))
                {
                    dialogInterface.dismiss();;
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder;
    }

    @Override
    public void setupView() {
        setSupportActionBar(mToolbar);
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong(Constants.KEY_ID_EXTRAS);
        mChroniclePageAdapter = new ChroniclePageAdapter(getSupportFragmentManager(), id);
        chroniclePager.setAdapter(mChroniclePageAdapter);
    }
}

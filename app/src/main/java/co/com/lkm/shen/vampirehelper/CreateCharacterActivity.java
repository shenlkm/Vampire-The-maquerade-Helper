package co.com.lkm.shen.vampirehelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Contracts.Views.CreateCharacterView;
import co.com.lkm.shen.vampirehelper.Presenter.CreateCharacterActivityPresenter;

public class CreateCharacterActivity extends Activity implements CreateCharacterView ,AdapterView.OnItemSelectedListener  {

    @BindView(R.id.spinner_clans) Spinner mSpiner;
    @BindView(R.id.input_character_name) EditText inputCharacterName;
    @BindView(R.id.input_player_name) EditText inputPlayerName;
    @BindView(R.id.input_initiative) EditText inputInitiativeName;
    @BindView(R.id.clan_logo_selected) ImageView mImageView;

    private final static String KEY_ID_EXTRAS = "ID";
    private CreateCharacterActivityPresenter mPresenter;
    private  long id = 0L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id =  extras.getLong(KEY_ID_EXTRAS);
        }

        mPresenter = new CreateCharacterActivityPresenter(this);
        mPresenter.setupView();
    }

    @Override
    public void setupView() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.clan_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiner.setOnItemSelectedListener(this);
        mSpiner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mImageView.setImageResource(mPresenter.getResource(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mImageView.setImageResource(R.drawable.ic_logo_no_clan);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDistroy();
    }

    public void saveCharacter(View v){
        mPresenter.saveChatacter(inputPlayerName.getText().toString(),
                inputCharacterName.getText().toString(),
                inputInitiativeName.getText().toString(),
                getResources().getStringArray(R.array.clan_list)[mSpiner.getSelectedItemPosition()],
                id);
        finish();
    }
}
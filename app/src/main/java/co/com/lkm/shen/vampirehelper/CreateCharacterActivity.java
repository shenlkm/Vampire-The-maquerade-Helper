package co.com.lkm.shen.vampirehelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Contracts.Views.CreateCharacterView;
import co.com.lkm.shen.vampirehelper.Presenter.CreateCharacterActivityPresenter;

public class CreateCharacterActivity extends Activity implements CreateCharacterView {

    @BindView(R.id.spinner_clans) Spinner mSpiner;

    private CreateCharacterActivityPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        ButterKnife.bind(this);

        mPresenter = new CreateCharacterActivityPresenter(this);
        mPresenter.setupView();
    }

    @Override
    public void setupView() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.clan_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiner.setAdapter(adapter);
    }
}
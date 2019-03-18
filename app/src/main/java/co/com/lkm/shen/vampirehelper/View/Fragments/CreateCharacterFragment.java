package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class CreateCharacterFragment extends Fragment implements Injectable , AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinner_clans)
    Spinner mSpiner;
    @BindView(R.id.input_character_name)
    EditText inputCharacterName;
    @BindView(R.id.input_player_name) EditText inputPlayerName;
    @BindView(R.id.input_initiative) EditText inputInitiativeName;
    @BindView(R.id.clan_logo_selected)
    ImageView mImageView;

    private final static String KEY_ID_EXTRAS = "ID";
    private  long id = 0L;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_character, container, false);
        ButterKnife.bind(this, rootView);
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null){
            id =  extras.getLong(KEY_ID_EXTRAS);
        }
        setupView();
        return rootView;
    }

    private void setupView() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.clan_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiner.setOnItemSelectedListener(this);
        mSpiner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //mImageView.setImageResource(getActivity().getResource(position));
    }

    public void saveCharacter(View v){
        /*mPresenter.saveChatacter(inputPlayerName.getText().toString(),
                inputCharacterName.getText().toString(),
                inputInitiativeName.getText().toString(),
                mSpiner.getSelectedItemPosition(),
                id);
        finish();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mImageView.setImageResource(R.drawable.ic_logo_no_clan);
    }
}

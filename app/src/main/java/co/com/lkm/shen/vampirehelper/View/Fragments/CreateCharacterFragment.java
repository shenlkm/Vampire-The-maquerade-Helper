package co.com.lkm.shen.vampirehelper.View.Fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.ViewModel.CreateCharacterViewModel;
import co.com.lkm.shen.vampirehelper.di.Injectable;

public class CreateCharacterFragment extends Fragment implements Injectable , AdapterView.OnItemSelectedListener {


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public CreateCharacterViewModel mCreateCharacterViewModel;

    @BindView(R.id.spinner_clans)
    Spinner mSpiner;
    @BindView(R.id.input_character_name)
    EditText inputCharacterName;
    @BindView(R.id.input_player_name) EditText inputPlayerName;
    @BindView(R.id.input_initiative) EditText inputInitiativeName;
    @BindView(R.id.clan_logo_selected)
    ImageView mImageView;
    @BindView(R.id.save_character)
    Button saveCharacter;
    @BindView(R.id.switch_player)
    Switch switchPlayer;

    private long chronicle_id = 0L;

    public static CreateCharacterFragment newInstance(long id) {
        CreateCharacterFragment fragment = new CreateCharacterFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_character, container, false);
        ButterKnife.bind(this, rootView);
        setupView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCreateCharacterViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(CreateCharacterViewModel.class);
    }

    private void setupView() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.clan_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiner.setOnItemSelectedListener(this);
        mSpiner.setAdapter(adapter);

        saveCharacter.setOnClickListener((v -> saveCharacter(v)));
        switchPlayer.setChecked(true);
        switchPlayer.setOnCheckedChangeListener((v, checked) -> onToggle(checked));
    }

    private void onToggle(boolean checked) {
        inputPlayerName.setEnabled(checked);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mImageView.setImageResource(Constants.CLAN_LOGOS[position]);
    }

    public void saveCharacter(View v){
        Player player = new Player();
        String playerName = switchPlayer.isChecked() ? inputPlayerName.getText().toString() : "NPC";
        player.setName(playerName);
        player.setCharacterName(inputCharacterName.getText().toString());
        player.setInitiative(Integer.parseInt(inputInitiativeName.getText().toString()));
        player.setClan(mSpiner.getSelectedItemPosition());
        player.setChronicleId(chronicle_id);
        player.setPlayer(switchPlayer.isChecked());
        mCreateCharacterViewModel.insert(player);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        getFragmentManager().popBackStack();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mImageView.setImageResource(R.drawable.ic_logo_no_clan);
    }
}

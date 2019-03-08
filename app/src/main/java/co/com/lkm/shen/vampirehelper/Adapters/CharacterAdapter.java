package co.com.lkm.shen.vampirehelper.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.R;

public abstract class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private Context mContext;

    public CharacterAdapter(Context context) {
        mContext = context;
        setHasStableIds(false);
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_character, parent, false);
        return new CharacterAdapter.CharacterViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        /*final Player player = getItem(position);
        holder.chraracterName.setText(player.getCharacterName());
        holder.playerName.setText(String.format("Player name: %s", player.getName()));
        holder.initiative.setText(String.format("Initiative: %d", player.getInitiative()));
        holder.clanLogo.setImageResource(Constants.CLAN_LOGOS[player.getClan()]);*/
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.labelCharacterName) public TextView chraracterName;
        @BindView(R.id.labelPlayerName) public TextView playerName;
        @BindView(R.id.labelInitiative) public TextView initiative;
        @BindView(R.id.clan_logo) public ImageView clanLogo;
        @BindView(R.id.character_container) public ConstraintLayout parentLayout;

        public CharacterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

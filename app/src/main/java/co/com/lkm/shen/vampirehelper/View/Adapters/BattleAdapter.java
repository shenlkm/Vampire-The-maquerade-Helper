package co.com.lkm.shen.vampirehelper.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.R;

public class BattleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Player> mPayers;
    private List<Player> partyMembers;
    private boolean mPartyCreated;

    public BattleAdapter(Context context, boolean partyCreated) {
        mContext = context;
        mPartyCreated = partyCreated;
        partyMembers = new ArrayList<>();
        setHasStableIds(false);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View container;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case 0:
                container = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_battle_selection, parent, false);
                viewHolder = new BattleParticipantViewHolder(container);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindSelectionRow((BattleParticipantViewHolder) holder, position);
                break;
        }
    }

    private void bindSelectionRow(BattleParticipantViewHolder holder, int position) {
        final Player player = mPayers.get(position);
        holder.name.setText(String.format("%s (%s)", player.getCharacterName(), player.getName()));
        holder.clanLogo.setImageResource(Constants.CLAN_LOGOS[player.getClan()]);
        holder.initiative.setText(String.format("%d", player.getInitiative()));
        holder.plusInitiative.setOnClickListener(v -> { player.setInitiative(player.getInitiative()+1); notifyItemChanged(position);});
        holder.minusInitiative.setOnClickListener(v -> { reduceInitiative(player, position);});
        holder.playerSelected.setOnClickListener(v -> onCheckToggle((CheckBox) v, position));
    }

    private void onCheckToggle(CheckBox checkBox, int position) {
        if(checkBox.isChecked()){
            partyMembers.add (mPayers.get(position));
        } else if(partyMembers.contains(mPayers.get(position))){
            partyMembers.remove(mPayers.get(position));
        }
    }

    private void reduceInitiative(Player player, int position) {
        if(player.getInitiative() > 0){
            player.setInitiative(player.getInitiative()-1);
            notifyItemChanged(position);
        }
    }

    public static class BattleParticipantViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.player_selected)
        CheckBox playerSelected;
        @BindView(R.id.clan_logo)
        ImageView clanLogo;
        @BindView(R.id.label_name)
        TextView name;
        @BindView(R.id.label_initiative)
        TextView initiative;
        @BindView(R.id.minus_initiative)
        Button minusInitiative;
        @BindView(R.id.plus_initiative)
        Button plusInitiative;
        public BattleParticipantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public long getItemId(int index) {
        return mPayers.get(index).getId();
    }

    @Override
    public int getItemCount() {
        if (mPayers != null)
            return mPayers.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mPartyCreated ? 1 : 0;
    }

    public void setPlayers(List<Player> players){
        mPayers = players;
        notifyDataSetChanged();
    }

    public void createParty() {
        this.mPartyCreated = true;
        this.mPayers.clear();
        this.mPayers.addAll(partyMembers);
        notifyDataSetChanged();
    }
}

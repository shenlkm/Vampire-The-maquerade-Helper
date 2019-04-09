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
    private List<Player> mPlayers;
    private List<Player> partyMembers;
    private boolean mPartyCreated;

    public BattleAdapter(Context context, boolean partyCreated) {
        mContext = context;
        mPartyCreated = partyCreated;
        partyMembers = new ArrayList<>();
        mPlayers= new ArrayList<>();
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
            case 1:
                container = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_battle_status, parent, false);
                viewHolder = new BattleStatusViewHolder(container);
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
            case 1:
                bindStatusRow((BattleStatusViewHolder) holder, position);
                break;
        }
    }

    private void bindStatusRow(BattleStatusViewHolder holder, int position) {
        final Player player = mPlayers.get(position);
        holder.name.setText(String.format("%s (%s)", player.getCharacterName(), player.getName()));
        holder.clanLogo.setImageResource(Constants.CLAN_LOGOS[player.getClan()]);
    }

    private void bindSelectionRow(BattleParticipantViewHolder holder, int position) {
        final Player player = mPlayers.get(position);
        holder.name.setText(String.format("%s (%s)", player.getCharacterName(), player.getName()));
        holder.clanLogo.setImageResource(Constants.CLAN_LOGOS[player.getClan()]);
        holder.initiative.setText(String.format("%d", player.getInitiative()));
        holder.plusInitiative.setOnClickListener(v -> { player.setInitiative(player.getInitiative()+1); notifyItemChanged(position);});
        holder.minusInitiative.setOnClickListener(v -> { reduceInitiative(player, position);});
        holder.playerSelected.setOnClickListener(v -> onCheckToggle((CheckBox) v, position));
    }

    private void onCheckToggle(CheckBox checkBox, int position) {
        if(checkBox.isChecked()){
            partyMembers.add (mPlayers.get(position));
        } else if(partyMembers.contains(mPlayers.get(position))){
            partyMembers.remove(mPlayers.get(position));
        }
    }

    private void reduceInitiative(Player player, int position) {
        if(player.getInitiative() > 0){
            player.setInitiative(player.getInitiative()-1);
            notifyItemChanged(position);
        }
    }

    @Override
    public long getItemId(int index) {
        return mPlayers.get(index).getId();
    }

    @Override
    public int getItemCount() {
        if (mPlayers != null)
            return mPlayers.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mPartyCreated ? 1 : 0;
    }

    public void setPlayers(List<Player> players){

        this.mPlayers.clear();
        this.mPlayers.addAll(players);
        notifyDataSetChanged();
    }

    public List<Player> createParty() {
        this.mPartyCreated = true;
        this.mPlayers.clear();
        notifyDataSetChanged();
        return partyMembers;
    }

    public boolean isPartyCreated(){
        return this.mPartyCreated;
    }
    public void setPartyCreated(boolean value){
        mPartyCreated = value;
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

    public static class BattleStatusViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.clan_logo)
        ImageView clanLogo;
        @BindView(R.id.label_name)
        TextView name;
        public BattleStatusViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

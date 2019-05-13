package co.com.lkm.shen.vampirehelper.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        holder.initiative.setText(String.format(Locale.getDefault(), "%d", player.getInitiative()));
        holder.plusInitiative.setOnClickListener(v -> { player.setInitiative(player.getInitiative()+1); notifyItemChanged(position);});
        holder.minusInitiative.setOnClickListener(v -> reduceInitiative(player, position));
        holder.playerSelected.setOnClickListener(v -> onCheckToggle((CheckBox) v, position));
    }

    private void onCheckToggle(CheckBox checkBox, int position) {
        if(checkBox.isChecked()){
            partyMembers.add(mPlayers.get(position));
        } else {
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

        CheckBox playerSelected;
        ImageView clanLogo;
        TextView name;
        TextView initiative;
        Button minusInitiative;
        Button plusInitiative;
        public BattleParticipantViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class BattleStatusViewHolder extends RecyclerView.ViewHolder{

        ImageView clanLogo;
        TextView name;
        public BattleStatusViewHolder(View itemView) {
            super(itemView);
        }
    }
}

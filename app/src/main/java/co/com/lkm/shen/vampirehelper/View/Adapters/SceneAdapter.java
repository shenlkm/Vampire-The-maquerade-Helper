package co.com.lkm.shen.vampirehelper.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.View.Fragments.BattleFragment;
import co.com.lkm.shen.vampirehelper.View.HomeActivity;

public class SceneAdapter extends RecyclerView.Adapter <SceneAdapter.SceneViewHolder> {

    private Context mContext;
    private SimpleDateFormat simpleDateFormat;
    private List<Scene> mScenes;


    public SceneAdapter(Context context) {
        mContext = context;
        setHasStableIds(false);
        simpleDateFormat = new SimpleDateFormat(Constants.datePattern);
    }

    @NonNull
    @Override
    public SceneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_scene, parent, false);
        return new SceneAdapter.SceneViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull SceneViewHolder holder, int position) {
        final Scene scene = mScenes.get(position);
        //holder.characterIconBadge.setText(""+scene.getFighters().size());
        holder.npcIconBadge.setText("0");
        holder.sceneName.setText(scene.getName());
        holder.startDate.setText(simpleDateFormat.format(scene.getStartDate()));
        String endDate = scene.getEndtDate() == null ? "On going" : simpleDateFormat.format(scene.getEndtDate());
        holder.endDate.setText(endDate);

        holder.parentLayout.setOnClickListener((v -> onItemSelected(v, scene)));
    }

    private void onItemSelected(View v, Scene scene) {
        BattleFragment fragment = BattleFragment.newInstance(scene.getChronicleId(),scene.getId());
        ((HomeActivity) mContext).replaceFragment(fragment);
    }

    @Override
    public int getItemCount() {
        if (mScenes != null)
            return mScenes.size();
        else return 0;
    }

    public static class SceneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.character_icon_badge)
        public TextView characterIconBadge;
        @BindView(R.id.npc_icon_badge)
        public TextView npcIconBadge;
        @BindView(R.id.sceneName)
        public TextView sceneName;
        @BindView(R.id.startDate)
        public TextView startDate;
        @BindView(R.id.endDate)
        public TextView endDate;
        @BindView(R.id.scene_row_container)
        public ConstraintLayout parentLayout;

        public SceneViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setScenes(List<Scene> scenes){
        mScenes = scenes;
        notifyDataSetChanged();
    }
}

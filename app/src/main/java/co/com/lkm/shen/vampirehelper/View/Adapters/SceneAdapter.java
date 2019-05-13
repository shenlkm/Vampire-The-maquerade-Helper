package co.com.lkm.shen.vampirehelper.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Scene;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.View.Fragments.BattleFragment;
import co.com.lkm.shen.vampirehelper.View.HomeActivity;

public class SceneAdapter extends RecyclerView.Adapter <SceneAdapter.SceneViewHolder> {

    private Context mContext;
    private SimpleDateFormat simpleDateFormat;
    private List<Scene> mScenes;


    public SceneAdapter(Context context) {
        mContext = context;
        setHasStableIds(false);
        simpleDateFormat = new SimpleDateFormat(Constants.datePattern, Locale.US);
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
        public TextView characterIconBadge;
        public TextView npcIconBadge;
        public TextView sceneName;
        public TextView startDate;
        public TextView endDate;
        public ConstraintLayout parentLayout;

        public SceneViewHolder(View view) {
            super(view);
        }
    }

    public void setScenes(List<Scene> scenes){
        mScenes = scenes;
        notifyDataSetChanged();
    }
}

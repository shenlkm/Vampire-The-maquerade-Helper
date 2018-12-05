package co.com.lkm.shen.vampirehelper.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.lkm.shen.vampirehelper.Constants;
import co.com.lkm.shen.vampirehelper.Domain.Scene;
import co.com.lkm.shen.vampirehelper.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class SceneAdapter extends RealmRecyclerViewAdapter<Scene, SceneAdapter.SceneViewHolder> {

    private Context mContext;
    private SimpleDateFormat simpleDateFormat;

    public SceneAdapter(Context context, OrderedRealmCollection<Scene> scenes) {
        super(scenes,  true );
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
        final Scene scene = getItem(position);
        holder.characterIconBadge.setText(""+scene.getFighters().size());
        holder.npcIconBadge.setText("0");
        holder.sceneName.setText(scene.getName());
        holder.startDate.setText(simpleDateFormat.format(scene.getStartDate()));
        String endDate = scene.getEndtDate() == null ? "On going" : simpleDateFormat.format(scene.getEndtDate());
        holder.endDate.setText(endDate);
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

        public SceneViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

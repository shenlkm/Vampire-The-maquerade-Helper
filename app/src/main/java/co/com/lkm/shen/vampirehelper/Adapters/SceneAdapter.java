package co.com.lkm.shen.vampirehelper.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import co.com.lkm.shen.vampirehelper.Domain.Scene;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class SceneAdapter extends RealmRecyclerViewAdapter<Scene, SceneAdapter.SceneViewHolder> {
    public SceneAdapter(Context context, OrderedRealmCollection<Scene> scenes) {
        super(scenes,  true );
    }

    @NonNull
    @Override
    public SceneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SceneViewHolder holder, int position) {

    }

    public static class SceneViewHolder extends RecyclerView.ViewHolder {
        public SceneViewHolder(View itemView) {
            super(itemView);
        }
    }
}

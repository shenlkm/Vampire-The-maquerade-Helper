package co.com.lkm.shen.vampirehelper.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.com.lkm.shen.vampirehelper.Domain.Player;
import co.com.lkm.shen.vampirehelper.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class CharacterAdapter extends RealmRecyclerViewAdapter<Player, CharacterAdapter.CharacterViewHolder> {

    private Context mContext;

    public CharacterAdapter(Context context, @Nullable OrderedRealmCollection<Player> data) {
        super(data, true);
        mContext = context;
        setHasStableIds(false);
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.chronicle_row, parent, false);
        return new CharacterAdapter.CharacterViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        final Player chronicle = getItem(position);
        holder.mChronicleName.setText(chronicle.getName());
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        public TextView mChronicleName;
        public ConstraintLayout parentLayout;

        public CharacterViewHolder(View view) {
            super(view);
            parentLayout = view.findViewById(R.id.chronicle__row_layout);
            mChronicleName = view.findViewById(R.id.chronicleName);
        }
    }
}

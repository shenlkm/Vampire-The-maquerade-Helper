package co.com.lkm.shen.vampirehelper.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import co.com.lkm.shen.vampirehelper.Domain.Chronicle;
import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.ScenesActivity;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class ChronicleAdapter extends RealmRecyclerViewAdapter<Chronicle, ChronicleAdapter.ChronicleViewHolder> {

    private Context mContext;

    public ChronicleAdapter(Context context, OrderedRealmCollection<Chronicle> chronicles)
    {
        super(chronicles, true);
        mContext = context;
        setHasStableIds(true);
    }

    public static class ChronicleViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mChronicleName;
        public ConstraintLayout parentLayout;

        public ChronicleViewHolder(View view) {
            super(view);
            parentLayout = view.findViewById(R.id.chronicle__row_layout);
            mChronicleName = view.findViewById(R.id.chronicleName);
        }
    }

    @Override
    public ChronicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.chronicle_row, parent, false);
        return new ChronicleViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(ChronicleViewHolder holder, int position) {
        final Chronicle chronicle = getItem(position);
        holder.mChronicleName.setText(chronicle.getName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ScenesActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int index) {
        //noinspection ConstantConditions
        return getItem(index).getId();
    }


}

package co.com.lkm.shen.vampirehelper.View.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.lkm.shen.vampirehelper.R;
import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Chronicle;
import co.com.lkm.shen.vampirehelper.View.Fragments.DashboardFragment;
import co.com.lkm.shen.vampirehelper.View.HomeActivity;

public class ChronicleAdapter extends RecyclerView.Adapter<ChronicleAdapter.ChronicleViewHolder> {

    private Context mContext;

    private List<Chronicle> mChronicles;

    public ChronicleAdapter(Context context)
    {
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

        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chronicle, parent, false);
        return new ChronicleViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(ChronicleViewHolder holder, int position) {
        final Chronicle chronicle = mChronicles.get(position);
        holder.mChronicleName.setText(chronicle.getName());
        holder.parentLayout.setOnClickListener((v -> onItemSelected(v, chronicle)));
    }

    private void onItemSelected(View v, Chronicle chronicle) {
        DashboardFragment fragment = DashboardFragment.newInstance(chronicle.getId());
        ((HomeActivity) mContext).replaceFragment(fragment);
    }

    public void setChronicles(List<Chronicle> chronicles){
        mChronicles = chronicles;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int index) {
        return mChronicles.get(index).getId();
    }

    @Override
    public int getItemCount() {
        if (mChronicles != null)
            return mChronicles.size();
        else return 0;
    }
}

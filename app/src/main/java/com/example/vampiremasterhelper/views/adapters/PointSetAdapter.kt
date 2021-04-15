package com.example.vampiremasterhelper.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.PointSetItemViewBinding
import com.example.vampiremasterhelper.model.PointItemModel


class PointSetAdapter(private val dataSet: List<PointItemModel>) : RecyclerView.Adapter<PointSetAdapter.PointSetViewHolder>() {

    private lateinit var binding: PointSetItemViewBinding

    class PointSetViewHolder(private val holderBinding: PointSetItemViewBinding) : RecyclerView.ViewHolder(holderBinding.root) {

        fun bind(item: PointItemModel) {
            holderBinding.pivItem.setLabel(item.label)
            holderBinding.pivItem.setFilledPoints(item.filled)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointSetViewHolder {
        binding = PointSetItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PointSetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointSetViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }
}
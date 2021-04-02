package com.example.vampiremasterhelper.views

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.PointSetItemViewBinding
import com.example.vampiremasterhelper.model.PointItemModel


class PointSetAdapter(private val dataSet: Array<PointItemModel>) : RecyclerView.Adapter<PointSetAdapter.PointSetViewHolder>() {

    private lateinit var binding: PointSetItemViewBinding

    class PointSetViewHolder(val binding: PointSetItemViewBinding) : RecyclerView.ViewHolder(binding.root){

    private var  holderBinding: PointSetItemViewBinding = binding

        fun bind(item: PointItemModel) {
            holderBinding.pivItem.setLabel(item.label)
            holderBinding.pivItem.setOnClickListener {
                holderBinding.pivItem.fillPoint()
            }
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
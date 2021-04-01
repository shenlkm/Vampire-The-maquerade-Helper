package com.example.vampiremasterhelper.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.PointSetItemViewBinding
import com.example.vampiremasterhelper.model.PointItemModel

class PointSetAdapter(private val dataSet: Array<PointItemModel>) : RecyclerView.Adapter<PointSetAdapter.PointSetViewHolder>() {

    private lateinit var binding: PointSetItemViewBinding

    class PointSetViewHolder(view: View): RecyclerView.ViewHolder(view) {

        init {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointSetViewHolder {
        binding = PointSetItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointSetViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PointSetViewHolder, position: Int) {
        binding.pivItem.setLabel(dataSet[position].label)
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }
}
package com.example.vampiremasterhelper.views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.BloodPointViewBinding

class BloodPoolAdapter (private val points: Int) : RecyclerView.Adapter<BloodPoolAdapter.BloodPoolViewHolder>() {

    private lateinit var binding: BloodPointViewBinding

    class BloodPoolViewHolder(val binding: BloodPointViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var  holderBinding: BloodPointViewBinding = binding

        fun bind(index: Int) {
            holderBinding.spvBloodPoint.setState(0)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodPoolViewHolder {
        binding = BloodPointViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BloodPoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BloodPoolViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return points
    }
}
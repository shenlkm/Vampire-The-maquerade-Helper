package com.example.vampiremasterhelper.views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.BloodPointViewBinding

class BloodPoolAdapter (private val points: Int) : RecyclerView.Adapter<BloodPoolAdapter.BloodPoolViewHolder>() {

    private lateinit var binding: BloodPointViewBinding
    private var usedPoints: Int  = 0

    class BloodPoolViewHolder(val binding: BloodPointViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var  holderBinding: BloodPointViewBinding = binding

        fun redraw(index: Int, usedPoints: Int) {
            if (index < usedPoints) {
                holderBinding.spvBloodPoint.setState(1)
            } else {
                holderBinding.spvBloodPoint.setState(0)
            }

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
        holder.redraw(position, usedPoints)
        holder.binding.spvBloodPoint.setOnClickListener {
            modifyBloodPool(false)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return points
    }

    private fun modifyBloodPool(reduce: Boolean) {
        if (reduce && points - usedPoints > 0) {
            usedPoints++
        } else if (!reduce && usedPoints > 0) {
            usedPoints--
        }
    }
}
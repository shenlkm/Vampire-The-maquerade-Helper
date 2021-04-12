package com.example.vampiremasterhelper.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.SelectDialogItemListBinding
import com.example.vampiremasterhelper.model.DialogSelectItem

class SelectDialogAdapter<T> constructor(private val items: List<T>, val onItemSelected: (item: T?) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() where T : DialogSelectItem {

    private lateinit var binding: SelectDialogItemListBinding

    class SelectDialogViewHolder<T>(private val holderBinding: SelectDialogItemListBinding) : RecyclerView.ViewHolder(holderBinding.root) where T : DialogSelectItem {
        fun bind(item: T) {
            holderBinding .tvItemLabel.text = item.value
            holderBinding.clDialogItemContainer.isSelected = item.selected
            //if (item.selected) holderBinding .tvItemLabel.setTextAppearance(R.style.TextRobotoM_M_B) else holderBinding .tvItemLabel.setTextAppearance(R.style.TextRobotoR_M_B)
        }
    }

    class SectionSelectDialogViewHolder<T>(private val holderBinding: SelectDialogItemListBinding) : RecyclerView.ViewHolder(holderBinding.root) where T : DialogSelectItem {
        fun bind(item: T) {
            holderBinding .tvItemLabel.text = item.value
            holderBinding.clDialogItemContainer.isEnabled = false
            //tv_item_label.setTextAppearance(R.style.TextRobotoR_M_B040)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = SelectDialogItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return if (viewType == 0) SelectDialogViewHolder<T>(binding) else {
            binding.itemSeparator.visibility = View.INVISIBLE
            SectionSelectDialogViewHolder<T>(binding)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isSection) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            binding.clDialogItemContainer.setOnClickListener {
                if (!items[position].selected) {
                    for (item in items) {
                        item.selected = false
                    }
                    items[position].selected = true
                    notifyDataSetChanged()
                }
                onItemSelected(items[position])
            }
            (holder as SelectDialogViewHolder<T>).bind(items[position])
        } else {
            (holder as SectionSelectDialogViewHolder<T>).bind(items[position])
        }
    }
}
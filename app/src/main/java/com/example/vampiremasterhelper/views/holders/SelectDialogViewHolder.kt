package com.example.vampiremasterhelper.views.holders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vampiremasterhelper.databinding.SelectDialogListViewBinding
import com.example.vampiremasterhelper.model.DialogSelectItem
import com.example.vampiremasterhelper.views.adapters.SelectDialogAdapter

class SelectDialogViewHolder <T> constructor(private val dataSet: List<T>, val context: Context) where T : DialogSelectItem {

    fun getView(title: String, onItemSelected: (item: T?) -> Unit): View {
        val binding: SelectDialogListViewBinding = SelectDialogListViewBinding.inflate(
            LayoutInflater.from(context), null, false)
        binding.tvSelectDialogTitle.text = title
        val viewAdapter = SelectDialogAdapter<T>(dataSet, onItemSelected)
        binding.rvSelectDialogList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }
        return binding.root
    }

}
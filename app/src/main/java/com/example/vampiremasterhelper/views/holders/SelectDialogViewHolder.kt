package com.example.vampiremasterhelper.views.holders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.model.DialogSelectItem
import com.example.vampiremasterhelper.views.adapters.SelectDialogAdapter

class SelectDialogViewHolder <T> constructor(private val dataSet: List<T>, val context: Context) where T : DialogSelectItem {

    fun getView(title: String, onItemSelected: (item: T?) -> Unit): View {
        val root = LayoutInflater.from(context).inflate(R.layout.select_dialog_list_view, null, false)
        val tvSelectDialogTitle = root.findViewById<TextView>(R.id.tv_select_dialog_title)
        val rvSelectDialogList = root.findViewById<RecyclerView>(R.id.rv_select_dialog_list)
        tvSelectDialogTitle.text = title
        val viewAdapter = SelectDialogAdapter<T>(dataSet, onItemSelected)
        rvSelectDialogList.layoutManager = LinearLayoutManager(context)
        rvSelectDialogList.adapter = viewAdapter
        return root
    }

}
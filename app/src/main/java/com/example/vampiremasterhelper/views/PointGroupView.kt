package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vampiremasterhelper.databinding.PointGroupViewBinding
import com.example.vampiremasterhelper.model.PointGroupModel

class PointGroupView @JvmOverloads constructor(context: Context,
                                               attributeSet: AttributeSet? = null,
                                               defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var  binding: PointGroupViewBinding = PointGroupViewBinding.inflate(LayoutInflater.from(context), this, false)

        init {
            addView(binding.root)
        }

    private fun setGroupTitle(title: String) {
        binding.tvPointGroupLabel.text = title
    }

    fun setData(data: PointGroupModel) {
        setGroupTitle(data.title)
        data.items.let {
            binding.rvGroupOfAttributes.layoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvGroupOfAttributes.adapter = PointSetAdapter(it)
        }
    }
}
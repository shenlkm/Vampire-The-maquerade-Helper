package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vampiremasterhelper.databinding.PointGroupSetViewBinding
import com.example.vampiremasterhelper.databinding.PointGroupViewBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointGroupSetModel

class PointGroupSetView @JvmOverloads constructor(context: Context,
                                                  attributeSet: AttributeSet? = null,
                                                  defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var  binding: PointGroupSetViewBinding = PointGroupSetViewBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun setGroupTitle(title: String) {
        binding.tvPointGroupSetTitle.setText(title)
    }

    fun setData(data: PointGroupSetModel) {
        setGroupTitle(data.title)

        data.items.let {
            binding.pgvOne.setData(it[0])
            binding.pgvTwo.setData(it[1])
            binding.pgvThree.setData(it[2])
        }
    }
}
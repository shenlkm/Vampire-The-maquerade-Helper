package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.databinding.PointGroupSetViewBinding
import com.example.vampiremasterhelper.model.PointGroupSetModel

class PointGroupSetView @JvmOverloads constructor(context: Context,
                                                  attributeSet: AttributeSet? = null,
                                                  defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var  binding: PointGroupSetViewBinding = PointGroupSetViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var isVisible = true

    init {
        addView(binding.root)
        binding.ivExpand.setOnClickListener {
            if (isVisible) {
                binding.pgvOne.visibility = GONE
                binding.pgvTwo.visibility = GONE
                binding.pgvThree.visibility = GONE
                binding.ivExpand.setImageResource(android.R.drawable.arrow_down_float)
            } else {
                binding.pgvOne.visibility = VISIBLE
                binding.pgvTwo.visibility = VISIBLE
                binding.pgvThree.visibility = VISIBLE
                binding.ivExpand.setImageResource(android.R.drawable.arrow_up_float)
            }
            isVisible = !isVisible
        }
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
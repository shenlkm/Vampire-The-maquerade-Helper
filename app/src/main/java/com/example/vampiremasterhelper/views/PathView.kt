package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.databinding.PathViewBinding
import com.example.vampiremasterhelper.model.PathModel

class PathView @JvmOverloads constructor(context: Context,
                                         attributeSet: AttributeSet? = null,
                                         defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding: PathViewBinding = PathViewBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        binding.pivChosenPath.setOnClickListener {
            setBearingModifier(binding.pivChosenPath.fillPoint())
        }
    }

    fun setData(data: PathModel) {
        binding.pivChosenPath.setLabel(data.path)
        binding.tvBearingValue.text = data.bearing
        binding.pivChosenPath.setFilledPoints(data.points)
        setBearingModifier(data.points)
    }

    private fun setBearingModifier(points: Int) {
        binding.tvBearingModifier.text = when (points) {
            1 -> "( +2 )"
            2,3 -> "( +1 )"
            8,9 -> "( -1 )"
            10 -> "( -2 )"
            else -> "( 0 )"
        }
    }
}
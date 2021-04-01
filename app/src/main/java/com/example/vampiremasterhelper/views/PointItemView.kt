package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.databinding.PointItemViewBinding

class PointItemView @JvmOverloads constructor(context: Context,
                                              attributeSet: AttributeSet? = null,
                                              defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var  binding: PointItemViewBinding = PointItemViewBinding.inflate(LayoutInflater.from(context), this, false)
    var filledPoints: Int = 1
    var temporalPoints: Int = 3

    init {
        addView(binding.root)
        setup()
    }

    private fun setup() {
        var i = 0
        var lastFilled = filledPoints
        while (i < filledPoints) {
            (binding.llPointContainer.getChildAt(i) as PointView).setState(2)
            i++
        }
        while (lastFilled < temporalPoints) {
            (binding.llPointContainer.getChildAt(lastFilled) as PointView).setState(1)
            lastFilled++
        }
    }

    fun setLabel(label: String) {
        binding.tvItemPointLabel.text = label
    }

    companion object {
        const val maxPoints = 5
    }
}
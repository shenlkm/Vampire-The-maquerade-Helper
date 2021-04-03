package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.databinding.PointItemViewBinding


class PointItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var  binding: PointItemViewBinding = PointItemViewBinding.inflate(
        LayoutInflater.from(
            context
        ), this, false
    )
    private var filledPoints: Int = 1
    private var temporalPoints: Int = 0
    private var length: Int = 5
    private var equalWeight: Boolean = false

    init {
        addView(binding.root)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PointItemView)
        length = attributes.getInt(R.styleable.PointItemView_pointLength, 5)
        equalWeight = attributes.getBoolean(R.styleable.PointItemView_equalWeight, false)
        attributes.getString(R.styleable.PointItemView_label)?.let {
            setLabel(it)
        }
        setup()
        attributes.recycle()
    }

    private fun setup() {
        for (i in 0 until length) {
            val pointView = PointView(context)
            if (i < filledPoints) {
                pointView.setState(2)
            }
            if (equalWeight) {
                val param = LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT,
                    1.0f
                )
                pointView.layoutParams = param
            }
            binding.llPointContainer.addView(pointView)
        }
        var lastFilled = filledPoints
        while (lastFilled < temporalPoints) {
            (binding.llPointContainer.getChildAt(lastFilled) as PointView).setState(1)
            lastFilled++
        }
    }

    fun fillPoint() {
        if (filledPoints+1 <= length) {
            (binding.llPointContainer.getChildAt(filledPoints) as PointView).setState(2)
            filledPoints++
        }
    }

    fun setLabel(label: String) {
        binding.tvItemPointLabel.text = label
    }
}
package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.databinding.PointViewBinding

class PointView @JvmOverloads constructor(context: Context,
                            attributeSet: AttributeSet? = null,
                            defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding = PointViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var state: Int

    init {
        addView(binding.root)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PointView)
        state = attributes.getInt(R.styleable.PointView_pointState, 0)
        setState()
        attributes.recycle()
    }

    fun setState(state: Int) {
        this.state = state
        setState()
    }

    private fun setState() {
        when (state) {
            1 -> binding.root.setBackgroundResource(R.drawable.ic_temporal_point)
            2 -> binding.root.setBackgroundResource(R.drawable.ic_filled_point)
            else -> binding.root.setBackgroundResource(R.drawable.ic_empty_point)
        }
    }
}


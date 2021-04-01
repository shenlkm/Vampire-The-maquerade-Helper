package com.example.vampiremasterhelper.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.vampiremasterhelper.R

class PointView @JvmOverloads constructor(context: Context,
                            attributeSet: AttributeSet? = null,
                            defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var state: Int

    init {
        inflate(context, R.layout.point_view, this)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PointView)
        state = attributes.getInt(R.styleable.PointView_state, 0)
        setState()
        attributes.recycle()
    }

    fun setState(state: Int) {
        this.state = state
        setState()
    }

    private fun setState() {
        when (state) {
            1 -> this.setBackgroundResource(R.drawable.ic_temporal_point)
            2 -> this.setBackgroundResource(R.drawable.ic_filled_point)
            else -> this.setBackgroundResource(R.drawable.ic_empty_point)
        }
    }
}


package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.databinding.SquarePointViewBinding

class SquarePointView @JvmOverloads constructor(context: Context,
                                  attributeSet: AttributeSet? = null,
                                  defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding = SquarePointViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var state: Int

    init {
        addView(binding.root)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PointView)
        state = attributes.getInt(R.styleable.SquarePointView_squareState, 0)
        setState()
        attributes.recycle()
    }

    fun setState(state: Int) {
        this.state = state
        setState()
    }

    fun getState() = state

    private fun setState() {
        when (state) {
            1 -> binding.root.setBackgroundResource(R.drawable.ic_squere_point_used)
            2 -> binding.root.setBackgroundResource(R.drawable.ic_squere_point_lethal)
            3 -> binding.root.setBackgroundResource(R.drawable.ic_square_point_aggravated)
            else -> binding.root.setBackgroundResource(R.drawable.ic_squere_point)
        }
    }
}
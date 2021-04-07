package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.vampiremasterhelper.databinding.WillpowerViewBinding

class WillPowerView @JvmOverloads constructor(context: Context,
                                                  attributeSet: AttributeSet? = null,
                                                  defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding: WillpowerViewBinding = WillpowerViewBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        setup()
    }

    private fun setup() {
        for (i in 0 until 10) {
            val squarePointView = SquarePointView(context)
            val param = LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT,
                1.0f
            )
            squarePointView.layoutParams = param

            squarePointView.setOnClickListener {
                val index = binding.llTempPointContainer.indexOfChild(it)
                if (index < binding.pivWillpowerPermanentPoints.totalPoints) {
                    val newState = if (squarePointView.getState() == 0) 1 else 0
                    squarePointView.setState(newState)
                }
            }
            binding.llTempPointContainer.addView(squarePointView)
        }
    }
}
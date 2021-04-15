package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.vampiremasterhelper.databinding.WillpowerViewBinding
import com.example.vampiremasterhelper.views.listener.PointItemViewListener

class WillPowerView @JvmOverloads constructor(context: Context,
                                                  attributeSet: AttributeSet? = null,
                                                  defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr), PointItemViewListener {

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
                onSquarePointClicked(it, squarePointView)
            }
            binding.pivWillpowerPermanentPoints.setViewDataChangeListener(this)
            binding.llTempPointContainer.addView(squarePointView)
        }
    }

    private fun onSquarePointClicked(it: View, squarePointView: SquarePointView) {
        val index = binding.llTempPointContainer.indexOfChild(it)
        if (index < binding.pivWillpowerPermanentPoints.totalPoints) {
            val newState = if (squarePointView.getState() == 0) 1 else 0
            squarePointView.setState(newState)
        }
    }

    override fun onPunctuationChanged(before: Int, after: Int) {
        for (i in  after until 10) {
            binding.llTempPointContainer.getChildAt(i)?.let {
                (it as SquarePointView).setState(0)
            }
        }
    }

}
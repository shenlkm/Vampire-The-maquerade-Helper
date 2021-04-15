package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.databinding.PointItemViewBinding
import com.example.vampiremasterhelper.views.listener.PointItemViewListener


class PointItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding: PointItemViewBinding = PointItemViewBinding.inflate(
        LayoutInflater.from(
            context
        ), this, false
    )

    private var filledPoints: Int = 0
    private var temporalPoints: Int = 0
    private var length: Int = 5
    private var equalWeight: Boolean = false
    private var autoFillPoints: Boolean = false

    private var dataChangeListener: PointItemViewListener? = null

    val totalPoints: Int
        get() = filledPoints + temporalPoints

    init {
        addView(binding.root)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PointItemView)
        length = attributes.getInt(R.styleable.PointItemView_pointLength, 5)
        equalWeight = attributes.getBoolean(R.styleable.PointItemView_equalWeight, false)
        autoFillPoints = attributes.getBoolean(R.styleable.PointItemView_autoFillPoints, true)
        attributes.getString(R.styleable.PointItemView_label)?.let {
            setLabel(it)
        }
        setup()
        attributes.recycle()
    }

    private fun setup() {
        addPointsToView()
        setTemporalPoints()
        redrawPoints()
    }

    private fun addPointsToView() {
        for (i in 0 until length) {
            val pointView = PointView(context)
            if (equalWeight) {
                val param = LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT,
                    1.0f
                )
                pointView.layoutParams = param
            }
            if (autoFillPoints) {
                pointView.setOnClickListener {
                    setFilledPoints(binding.llPointContainer.indexOfChild(it) + 1)
                }
            }
            binding.llPointContainer.addView(pointView)
        }
    }

    private fun setTemporalPoints() {
        var lastFilled = filledPoints
        while (lastFilled < temporalPoints) {
            setPointStateAtIndex(lastFilled, 1)
            lastFilled++
        }
    }

    private fun redrawPoints() {
        for (i in 0 until binding.llPointContainer.childCount) {
            when {
                i < filledPoints -> setPointStateAtIndex(i, 2)
                i >= filledPoints && i < filledPoints + temporalPoints -> setPointStateAtIndex(i, 1)
                else -> setPointStateAtIndex(i, 0)
            }
        }
    }

    private fun setPointStateAtIndex(index: Int, state: Int) {
        (binding.llPointContainer.getChildAt(index) as PointView).setState(state)
    }

    fun setFilledPoints(value: Int) {
        this.filledPoints = value
        redrawPoints()
        dataChangeListener?.onPunctuationChanged(totalPoints)
    }

    fun fillPoint(): Int {
        if (filledPoints + 1 <= length) {
            setPointStateAtIndex(filledPoints, 2)
            filledPoints++
        }
        dataChangeListener?.onPunctuationChanged(totalPoints)
        return filledPoints
    }

    fun setLabel(label: String) {
        binding.tvItemPointLabel.text = label
    }

    fun setViewDataChangeListener(listener: PointItemViewListener) {
        dataChangeListener = listener
    }
}
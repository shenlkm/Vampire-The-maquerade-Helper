package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.databinding.PointItemViewBinding
import com.example.vampiremasterhelper.model.PointItemModel
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

    private var pointItem: PointItemModel = PointItemModel("", 0, 0)
    private var length: Int = 5
    private var equalWeight: Boolean = false
    private var autoFillPoints: Boolean = false

    private var dataChangeListener: PointItemViewListener? = null

    val totalPoints: Int
        get() = pointItem.filled + pointItem.temporal

    init {
        addView(binding.root)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PointItemView)
        length = attributes.getInt(R.styleable.PointItemView_pointLength, 5)
        equalWeight = attributes.getBoolean(R.styleable.PointItemView_equalWeight, false)
        autoFillPoints = attributes.getBoolean(R.styleable.PointItemView_autoFillPoints, true)
        binding.pointitem = pointItem
        attributes.getString(R.styleable.PointItemView_label)?.let {
            pointItem.label = it
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
        var lastFilled = pointItem.filled
        while (lastFilled < pointItem.temporal) {
            setPointStateAtIndex(lastFilled, 1)
            lastFilled++
        }
    }

    private fun redrawPoints() {
        for (i in 0 until binding.llPointContainer.childCount) {
            when {
                i < pointItem.filled -> setPointStateAtIndex(i, 2)
                i >= pointItem.filled && i < pointItem.filled + pointItem.temporal -> setPointStateAtIndex(i, 1)
                else -> setPointStateAtIndex(i, 0)
            }
        }
    }

    private fun setPointStateAtIndex(index: Int, state: Int) {
        (binding.llPointContainer.getChildAt(index) as PointView).setState(state)
    }

    fun setFilledPoints(value: Int) {
        this.pointItem.filled = value
        redrawPoints()
        dataChangeListener?.onPunctuationChanged(totalPoints)
    }

    fun fillPoint(): Int {
        if (pointItem.filled + 1 <= length) {
            setPointStateAtIndex(pointItem.filled, 2)
            pointItem.filled++
        }
        dataChangeListener?.onPunctuationChanged(totalPoints)
        return pointItem.filled
    }

    fun setLabel(label: String) {
        pointItem.label = label
    }

    fun setViewDataChangeListener(listener: PointItemViewListener) {
        dataChangeListener = listener
    }
}
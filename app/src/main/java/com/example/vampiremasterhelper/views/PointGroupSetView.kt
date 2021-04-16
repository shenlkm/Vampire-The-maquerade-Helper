package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.databinding.PointGroupSetViewBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.example.vampiremasterhelper.views.listener.PointItemViewListener

class PointGroupSetView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding: PointGroupSetViewBinding =
        PointGroupSetViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var isExpanded = true
    private var pointSetData: PointGroupSetModel = PointGroupSetModel("", mutableListOf())

    init {
        addView(binding.root)
        binding.isVisible = isExpanded
        binding.ivExpand.setOnClickListener {
            isExpanded = !isExpanded
            binding.isVisible = isExpanded
        }
    }

    fun setGroupTitle(title: String) {
        binding.tvPointGroupSetTitle.setTitle(title)
    }

    fun setIsExpanded(isExpanded: Boolean) {
        this.isExpanded = isExpanded
        binding.isVisible = isExpanded
    }

    fun getData(): PointGroupSetModel = pointSetData

    fun setData(data: PointGroupSetModel) {
        this.pointSetData.title = data.title
        setGroupTitle(data.title)
        this.pointSetData.items.clear()
        this.pointSetData.items.addAll(data.items)

        data.items.let { items ->
            setupPointGroup(binding.pgvOne, items[0], 0)
            setupPointGroup(binding.pgvTwo, items[1], 1)
            setupPointGroup(binding.pgvThree, items[2], 2)
        }
    }

    private fun setupPointGroup(pointGroupView: PointGroupView, item: PointGroupModel, index: Int) {
        pointGroupView.setData(item)
        pointGroupView.setViewDataChangeListener(object : PointItemViewListener {
            override fun onPunctuationChanged(before: Int, after: Int) {
                pointSetData.items[index].totalPoints = after
            }
        })
    }
}
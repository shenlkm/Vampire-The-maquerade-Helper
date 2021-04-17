package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.databinding.PointGroupSetViewBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.example.vampiremasterhelper.views.listener.PointItemViewListener
import com.example.vampiremasterhelper.views.listener.PointSetGroupViewListener

class PointGroupSetView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding: PointGroupSetViewBinding =
        PointGroupSetViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var isExpanded = true
    private var pointSetData: PointGroupSetModel = PointGroupSetModel("", mutableListOf())
    private var dataListener: PointSetGroupViewListener? = null

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
        this.updateData(data)

        setupPointGroup(binding.pgvOne,  0)
        setupPointGroup(binding.pgvTwo,  1)
        setupPointGroup(binding.pgvThree, 2)
    }

    fun updateData(data: PointGroupSetModel) {
        this.pointSetData.title = data.title
        setGroupTitle(data.title)
        this.pointSetData.items.clear()
        this.pointSetData.items.addAll(data.items)
        pointSetData.items.let { items ->
            binding.pgvOne.setData(items[0])
            binding.pgvTwo.setData(items[1])
            binding.pgvThree.setData(items[2])
        }
    }

    fun setViewDataChangeListener(listener: PointSetGroupViewListener) {
        dataListener = listener
    }

    private fun setupPointGroup(pointGroupView: PointGroupView, index: Int) {
        pointGroupView.setViewDataChangeListener(object : PointItemViewListener {
            override fun onPunctuationChanged(before: Int, after: Int) {
                pointSetData.items[index].totalPoints = after
                dataListener?.notifyPunctuationChange(this@PointGroupSetView)
            }
        })
    }
}
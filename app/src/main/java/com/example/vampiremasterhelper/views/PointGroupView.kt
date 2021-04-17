package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vampiremasterhelper.databinding.PointGroupViewBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointItemModel
import com.example.vampiremasterhelper.views.adapters.PointSetAdapter
import com.example.vampiremasterhelper.views.listener.PointItemViewListener

class PointGroupView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), PointItemViewListener {

    private var data: PointGroupModel = PointGroupModel("", listOf())
    private var binding: PointGroupViewBinding =
        PointGroupViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var dataChangeListener: PointItemViewListener? = null

    init {
        addView(binding.root)
        binding.pointGroup = data
    }

    fun setData(data: PointGroupModel) {
        this.data.title = data.title
        binding.pointGroup = data

        data.items.let { items ->
            binding.rvGroupOfAttributes.also {
                it.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                it.adapter = PointSetAdapter(items, this)
            }
        }
    }

    override fun onPunctuationChanged(before: Int, after: Int) {
        val before = this.data.totalPoints
        this.data.totalPoints += after-before
        dataChangeListener?.onPunctuationChanged(before, this.data.totalPoints)
    }

    fun setViewDataChangeListener(listener: PointItemViewListener) {
        dataChangeListener = listener
    }
}
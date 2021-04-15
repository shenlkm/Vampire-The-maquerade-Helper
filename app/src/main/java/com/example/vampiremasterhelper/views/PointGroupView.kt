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

class PointGroupView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private var data: PointGroupModel = PointGroupModel("", listOf<PointItemModel>().toTypedArray())
    private var binding: PointGroupViewBinding =
        PointGroupViewBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        binding.pointGroup = data
    }

    fun setData(data: PointGroupModel) {
        this.data.title = data.title
        data.items.let {
            binding.rvGroupOfAttributes.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = PointSetAdapter(it)
            }
        }
    }
}
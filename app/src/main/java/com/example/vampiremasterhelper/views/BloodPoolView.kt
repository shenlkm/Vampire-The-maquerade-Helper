package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vampiremasterhelper.databinding.BloodPoolViewBinding
import com.example.vampiremasterhelper.views.Adapters.BloodPoolAdapter

class BloodPoolView @JvmOverloads constructor(context: Context,
                                              attributeSet: AttributeSet? = null,
                                              defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding = BloodPoolViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var adapter: BloodPoolAdapter = BloodPoolAdapter(12)

    init {
        addView(binding.root)
        binding.rvBloodPool.layoutManager  = GridLayoutManager(context, 10)
        binding.rvBloodPool.adapter = adapter
    }

}
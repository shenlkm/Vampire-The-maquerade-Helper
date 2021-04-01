package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.vampiremasterhelper.databinding.TitleViewBinding

class TitleView @JvmOverloads constructor(context: Context,
                                          attributeSet: AttributeSet? = null,
                                          defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding = TitleViewBinding.inflate(LayoutInflater.from(context), this, false)

        init {
           addView(binding.root)
        }

    fun setText(title: String) {
        binding.tvTitle.text = title
    }
}
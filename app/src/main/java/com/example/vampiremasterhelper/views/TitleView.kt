package com.example.vampiremasterhelper.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.Dimension
import com.example.vampiremasterhelper.R
import com.example.vampiremasterhelper.databinding.TitleViewBinding

class TitleView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private var binding = TitleViewBinding.inflate(LayoutInflater.from(context), this, false)
    private var scale: Int = getContext().resources.displayMetrics.density.toInt()

    init {
           addView(binding.root)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.TitleView)
        if (attributes.getBoolean(R.styleable.TitleView_isSubtitle, false)) {
            setAsSubTitle()
        }
        attributes.getString(R.styleable.TitleView_title)?.let {
            setTitle(it)
        }
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setAsSubTitle() {
        binding.tvTitle.setTextSize(Dimension.SP, 16F)
        val pixels = (12 * scale + 0.5f).toInt()
        binding.vDecoratorLeft.layoutParams.width = pixels
        binding.vDecoratorLeft.layoutParams.height = pixels


        binding.vDecoratorRight.layoutParams.width = pixels
        binding.vDecoratorRight.layoutParams.height = pixels

        binding.vLineDecoratorLeft.layoutParams.height = 2
        binding.vLineDecorator.layoutParams.height = 2
    }
}
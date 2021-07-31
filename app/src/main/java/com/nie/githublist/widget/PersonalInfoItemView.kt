package com.nie.githublist.widget

import android.content.Context
import android.graphics.Paint
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.nie.githublist.R
import com.nie.githublist.databinding.ViewItemPersonalInfoBinding

class PersonalInfoItemView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy { ViewItemPersonalInfoBinding.inflate(LayoutInflater.from(context), this, true) }

    var text: String = ""
        set(value) {
            binding.textInfo.text = value
            field = value
        }

    init {
        val typeAdapter = context.obtainStyledAttributes(attrs, R.styleable.PersonalInfoItemView)
        val drawable = typeAdapter.getDrawable(R.styleable.PersonalInfoItemView_android_src)

        binding.imageIcon.setImageDrawable(drawable)

        typeAdapter.recycle()
    }

    fun setTextBaseLine(text: String) {
        binding.textInfo.paint.flags = Paint. UNDERLINE_TEXT_FLAG
        binding.textInfo.paint.isAntiAlias = true
        binding.textInfo.text = text
    }
}
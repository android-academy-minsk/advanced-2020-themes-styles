package com.alexeykatsuro.themestylesample

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.ImageViewCompat


class WelcomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.Widget_MyApp_WelcomeView
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val textView by lazy { findViewById<TextView>(R.id.text) }
    private val imageView by lazy { findViewById<ImageView>(R.id.image) }

    var icon: Drawable? = null
        set(value) {
            field = value
            imageView.setImageDrawable(value)
        }
    var text: String? = null
        set(value) {
            field = value
            textView.text = text
        }
    @ColorInt var textColor: Int = Color.BLACK
        set(value) {
            field = value
            textView.setTextColor(value)
        }

    init {
        inflate(context, R.layout.view_welcome, this)
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.WelcomeView, defStyleAttr, defStyleRes
        )

        text = a.getString(R.styleable.WelcomeView_text)
        icon = a.getDrawable(R.styleable.WelcomeView_img)
        textColor = a.getColor(R.styleable.WelcomeView_android_textColor, Color.BLACK)

        if (a.hasValue(R.styleable.WelcomeView_imgTint)) {
           setImageTint(a.getColor(R.styleable.WelcomeView_imgTint, Color.BLACK))
        }

        a.recycle()
    }

    fun setImageTint(@ColorInt tint: Int){
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(tint))
    }
}

package com.qwy.hencoderpracticedraw06.practice

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.qwy.chapter_03.R
import com.qwy.hencoderpracticedraw06.dpToPixel


class Practice05MultiProperties : ConstraintLayout {


    var animateBt: Button? = null
    var imageView: ImageView? = null
    var animated = false

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        animateBt = findViewById<View>(R.id.animateBt) as Button
        imageView = findViewById<View>(R.id.imageView) as ImageView
        imageView?.scaleX = 0f
        imageView?.scaleY = 0f
        imageView?.alpha = 0f
        animateBt?.setOnClickListener(OnClickListener {
            if (!animated) {
                imageView?.run {
                    animate()
                        .translationX(dpToPixel(200f))
                        .rotation(360f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                }
            } else {
                imageView?.run {
                    animate()
                        .translationX(0f)
                        .rotation(0f)
                        .scaleX(0f)
                        .scaleY(0f)
                        .alpha(0f)
                }
            }
            animated = !animated
        })


    }
}
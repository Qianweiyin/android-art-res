package com.qwy.hencoderpracticedraw06.practice

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.qwy.chapter_03.R


class Practice02Rotation : RelativeLayout {

    var animateBt: Button? = null
    var imageView: ImageView? = null
    var state = 0


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

        animateBt!!.setOnClickListener {
            when (state) {
                0 -> imageView!!.animate().rotation(180f)
                1 -> imageView!!.animate().rotation(0f)
                2 -> imageView!!.animate().rotationX(180f)
                3 -> imageView!!.animate().rotationX(0f)
                4 -> imageView!!.animate().rotationY(180f)
                5 -> imageView!!.animate().rotationY(0f)
            }
            state++
            if (state == 6) {
                state = 0
            }
        }
    }
}
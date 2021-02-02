package com.qwy.hencoderpracticedraw04.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class Practice03TranslateView : View {




    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


    }
}
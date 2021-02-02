package com.qwy.hencoderpracticedraw03.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Sample10SetTextAlignView : View {


    val paint = Paint()
    val text = "qianwin"


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paint.textSize = 60f

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        /**
         * setTextAlign(Paint.Align align)
         *
         * 设置文字的对齐方式。
         * 一共有三个值：LEFT、CENTER 和 RIGHT, 默认值为LEFT
         */

        paint.textAlign = Paint.Align.LEFT
        canvas?.drawText(text, 500f, 100f, paint)

        paint.textAlign = Paint.Align.CENTER
        canvas?.drawText(text, 500f, 200f, paint)

        paint.textAlign = Paint.Align.RIGHT
        canvas?.drawText(text, 500f, 300f, paint)
    }

}
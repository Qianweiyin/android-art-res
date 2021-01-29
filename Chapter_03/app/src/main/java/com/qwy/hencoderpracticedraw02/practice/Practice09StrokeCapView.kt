package com.qwy.hencoderpracticedraw02.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice09StrokeCapView  : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.strokeWidth = 40f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 使用 Paint.setStrokeCap() 来设置端点形状

        // 第一个：BUTT
        canvas?.drawLine(50f, 50f, 400f, 50f, paint)

        // 第二个：ROUND
        canvas?.drawLine(50f, 150f, 400f, 150f, paint)

        // 第三个：SQUARE
        canvas?.drawLine(50f, 250f, 400f, 250f, paint)
    }
}
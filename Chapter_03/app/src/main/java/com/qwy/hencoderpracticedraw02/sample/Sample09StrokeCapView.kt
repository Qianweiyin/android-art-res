package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Sample09StrokeCapView : View {
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
        paint.strokeCap = Paint.Cap.BUTT
        canvas?.drawLine(50f, 50f, 400f, 50f, paint)

        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(50f, 150f, 400f, 150f, paint)

        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(50f, 250f, 400f, 250f, paint)
    }
}
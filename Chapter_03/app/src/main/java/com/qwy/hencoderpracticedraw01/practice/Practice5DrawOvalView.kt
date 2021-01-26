package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Practice5DrawOvalView : View {
    private val paint: Paint = Paint()


    private val rectF = RectF(100f, 200f, 300f, 600f)


    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.isAntiAlias = true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        练习内容：使用 canvas.drawOval() 方法画椭圆

        canvas?.drawOval(rectF, paint)

    }
}
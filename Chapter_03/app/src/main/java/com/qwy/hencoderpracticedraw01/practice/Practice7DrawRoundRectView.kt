package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Practice7DrawRoundRectView : View {
    private val paint: Paint = Paint()
    private val rectF: RectF = RectF(100f, 100f, 400f, 200f)

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paint.color = Color.RED
        paint.isAntiAlias = true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形

        // TODO: 2021/1/26   rx/ry的意思
        canvas?.drawRoundRect(rectF, 80f, 80f, paint)

    }
}
package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Practice3DrawRectView : View {


    private val rectF: RectF = RectF(100f, 100f, 400f, 200f)
    private val paint: Paint = Paint()


    // TODO: 2021/1/26 Kotlin的构造函数
    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paint.style = Paint.Style.STROKE
        paint.color = Color.RED

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        练习内容：使用 canvas.drawRect() 方法画矩形

        canvas?.drawRect(rectF, paint)
    }
}
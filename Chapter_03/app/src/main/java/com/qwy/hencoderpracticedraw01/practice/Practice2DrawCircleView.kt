package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice2DrawCircleView : View {

    private val paint1 = Paint()
    private val paint2 = Paint()
    private val paint3 = Paint()
    private val paint4 = Paint()


    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {



        paint1.color = Color.BLACK
        paint1.style = Paint.Style.FILL
        paint1.isAntiAlias = true


        paint2.style = Paint.Style.STROKE
        paint2.isAntiAlias = true

        paint3.color = Color.BLUE
        paint3.isAntiAlias = true

        paint4.color = Color.RED
        paint4.style = Paint.Style.STROKE
        paint4.strokeWidth = 20f
        paint4.isAntiAlias = true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        canvas?.drawCircle(200f, 200f, 100f, paint1)
        canvas?.drawCircle(600f, 200f, 100f, paint2)

        canvas?.drawCircle(200f, 600f, 100f, paint3)

        canvas?.drawCircle(600f, 600f, 100f, paint4)


    }

}
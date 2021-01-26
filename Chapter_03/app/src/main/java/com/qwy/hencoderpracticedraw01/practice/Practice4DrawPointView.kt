package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice4DrawPointView : View {


    private val paintCircle = Paint()
    private val paintSquare = Paint()


    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paintCircle.style = Paint.Style.FILL
        paintCircle.color = Color.RED
        paintCircle.strokeCap = Paint.Cap.ROUND
        paintCircle.strokeWidth = 40f

        paintSquare.style = Paint.Style.FILL
        paintSquare.color = Color.BLUE
        paintSquare.strokeCap = Paint.Cap.BUTT
        paintSquare.strokeWidth = 40f


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //练习内容：使用 canvas.drawPoint() 方法画点
        //一个圆点，一个方点
        //圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点
        canvas?.drawPoint(400f, 400f, paintCircle)


        canvas?.drawPoint(600f, 400f, paintSquare)
    }
}
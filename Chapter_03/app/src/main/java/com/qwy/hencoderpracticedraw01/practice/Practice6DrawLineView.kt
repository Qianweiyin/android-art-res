package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice6DrawLineView : View {
    private val paint: Paint = Paint()

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
        //练习内容：使用 canvas.drawLine() 方法画直线

        canvas?.drawLine(100f, 100f, 600f, 100f, paint)

    }

}
package com.qwy.hencoderpracticedraw03.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice03SetTextSizeView : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "Hello HenCoder"

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
        var y = 100

        paint.textSize = 16f
        canvas?.drawText(text, 50f, y.toFloat(), paint)

        y += 30
        paint.textSize = 24f
        canvas?.drawText(text, 50f, y.toFloat(), paint)

        y += 55
        paint.textSize = 48f
        canvas?.drawText(text, 50f, y.toFloat(), paint)

        y += 80
        paint.textSize = 72f
        canvas?.drawText(text, 50f, y.toFloat(), paint)
    }
}
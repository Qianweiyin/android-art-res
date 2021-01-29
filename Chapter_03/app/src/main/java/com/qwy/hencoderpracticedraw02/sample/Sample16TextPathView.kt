package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Sample16TextPathView : View {


    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var pathPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var textPath: Path = Path()
    var text = "Hello HenCoder"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paint.textSize = 120f

        //None of the following functions can be called with the arguments supplied.
        paint.getTextPath(text, 0, text.length, 50f, 400f, textPath)

        pathPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(text, 50f, 200f, paint)

        canvas?.drawPath(textPath, pathPaint)
    }

}
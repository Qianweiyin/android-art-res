package com.qwy.hencoderpracticedraw03.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Practice01DrawTextView : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "AaJj钱维银"

    val path = Path()


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.textSize = 60f


        path.moveTo(50f, 100f)
        path.rLineTo(50f, 100f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(text, 50f, 100f, paint)



        canvas?.drawPath(path, paint) // 把 Path 也绘制出来，理解起来更方便
        canvas?.drawTextOnPath("Hello HeCoder", path, 0f, 0f, paint)

    }

}
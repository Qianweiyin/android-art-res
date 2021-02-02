package com.qwy.hencoderpracticedraw03.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice11GetFontSpacingView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "Hello HenCoder"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.textSize = 60f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val spacing = paint.fontSpacing

        canvas?.drawText(text, 50f, 100f, paint)

        canvas?.drawText(text, 50f, 100 + spacing, paint)

        canvas?.drawText(text, 50f, 100 + spacing * 2, paint)

    }
}
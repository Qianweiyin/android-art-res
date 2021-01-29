package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Sample13ShadowLayerView : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.setShadowLayer(10f, 5f, 5f, Color.RED)

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.textSize = 120f
        canvas?.drawText("Hello HenCoder", 50f, 200f, paint)
    }
}
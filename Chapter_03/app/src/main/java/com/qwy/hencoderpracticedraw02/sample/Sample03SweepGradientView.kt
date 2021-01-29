package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View


class Sample03SweepGradientView : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.shader = SweepGradient(
            300f, 300f, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3")
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(300f, 300f, 200f, paint)

    }

}
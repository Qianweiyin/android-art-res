package com.qwy.hencoderpracticedraw02.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Practice02RadialGradientView : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.shader = RadialGradient(
            300f, 300f, 200f, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"), Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(300f, 300f, 200f, paint)

    }
}
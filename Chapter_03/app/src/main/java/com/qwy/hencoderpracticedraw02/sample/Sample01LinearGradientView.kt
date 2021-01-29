package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class Sample01LinearGradientView @JvmOverloads constructor(
    ctz: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(ctz, attributeSet, defStyle) {


    private val linearGradientPaint by lazy {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = LinearGradient(
            100f, 100f, 500f, 500f, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"), Shader.TileMode.CLAMP
        )
        paint
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(300f, 300f, 200f, linearGradientPaint)

    }

}
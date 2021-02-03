package com.qwy.hencoderpracticedraw05.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class Sample05AfterOnDrawForegroundView : AppCompatImageView {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        paint.textSize = 60f

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.parseColor("#f44336")
        canvas?.drawRect(0f, 40f, 200f, 120f, paint)
        paint.color = Color.WHITE
        canvas?.drawText("New", 20f, 100f, paint)
    }
}
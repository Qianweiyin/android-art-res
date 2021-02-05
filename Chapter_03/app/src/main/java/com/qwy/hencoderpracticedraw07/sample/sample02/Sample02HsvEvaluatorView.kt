package com.qwy.hencoderpracticedraw07.sample.sample02

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Sample02HsvEvaluatorView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var color = 0xffff0000

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )


    fun getColor(): Int {
        return color.toInt()
    }

    fun setColor(color: Int) {
        this.color = color.toLong()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val width = width
        val height = height

        paint.color = color.toInt()
        canvas!!.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (width / 6).toFloat(),
            paint
        )
    }

}
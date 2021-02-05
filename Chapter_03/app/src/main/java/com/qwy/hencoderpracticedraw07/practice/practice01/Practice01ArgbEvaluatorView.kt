package com.qwy.hencoderpracticedraw07.practice.practice01

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.qwy.hencoderpracticedraw06.dpToPixel

class Practice01ArgbEvaluatorView : View {


    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var color = 0xffff0000

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        paint.textSize = dpToPixel(40f)
        paint.textAlign = Paint.Align.CENTER
    }


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
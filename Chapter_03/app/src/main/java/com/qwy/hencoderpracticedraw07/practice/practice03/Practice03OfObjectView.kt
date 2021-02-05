package com.qwy.hencoderpracticedraw07.practice.practice03

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.qwy.hencoderpracticedraw06.dpToPixel


class Practice03OfObjectView : View {


    companion object {
        //Const 'val' initializer should be a constant value
        val RADIUS: Float = dpToPixel(20f)
    }

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var mPositionF = PointF()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ){
        paint.color = Color.parseColor("#009688")
    }


    fun getPosition(): PointF {
        return mPositionF
    }

    fun setPosition(position: PointF) {
        position.set(position)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val innerPaddingLeft = RADIUS * 1
        val innerPaddingRight = RADIUS * 1
        val innerPaddingTop = RADIUS * 1
        val innerPaddingBottom = RADIUS * 3
        val width = width - innerPaddingLeft - innerPaddingRight - RADIUS * 2
        val height = height - innerPaddingTop - innerPaddingBottom - RADIUS * 2

        canvas!!.drawCircle(
            innerPaddingLeft + RADIUS + width * mPositionF.x,
            innerPaddingTop + RADIUS + height * mPositionF.y,
            RADIUS,
            paint
        )
    }
}
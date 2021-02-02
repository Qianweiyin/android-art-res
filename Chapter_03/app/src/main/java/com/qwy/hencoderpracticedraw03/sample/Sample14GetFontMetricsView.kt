package com.qwy.hencoderpracticedraw03.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Sample14GetFontMetricsView : View {


    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    var texts = arrayOf("A", "a", "J", "j", "Â", "â")
    var yOffset = 0f
    var mTop = 200
    var mBottom = 400

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint1.style = Paint.Style.STROKE
        paint1.strokeWidth = 20f
        paint1.color = Color.parseColor("#E91E63")
        paint2.textSize = 160f

        val fontMetrics = paint2.fontMetrics
        yOffset = -(fontMetrics.ascent + fontMetrics.descent) / 2
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        /**
         *
         * 2.2.2 FontMetrics getFontMetrics  获取Paint的FontMetrics
         *
         * FontMetrics 是个相对专业的工具类，它提供了几个文字排印方面的数值：
         * ascent,descent,top,bottom,leading。
         *
         * baseline :  作为文字显示的基准线;
         *
         * ascent/descent : 限制普通字符的顶部和底部范围。
         *
         * top/bottom : 限制所有字形的顶部和底部范围。
         *
         * leading : 指的是行的额外间距，即对于上下相邻的两行，上行的bottom线和下行的top线的距离。
         *
         */
        canvas?.drawRect(50f, mTop.toFloat(), (width - 50).toFloat(), mBottom.toFloat(), paint1)

        val middle = (mTop + mBottom) / 2
        canvas?.drawText(texts[0], 100f, middle + yOffset, paint2)
        canvas?.drawText(texts[1], 200f, middle + yOffset, paint2)
        canvas?.drawText(texts[2], 300f, middle + yOffset, paint2)
        canvas?.drawText(texts[3], 400f, middle + yOffset, paint2)
        canvas?.drawText(texts[4], 500f, middle + yOffset, paint2)
        canvas?.drawText(texts[5], 600f, middle + yOffset, paint2)
    }
}
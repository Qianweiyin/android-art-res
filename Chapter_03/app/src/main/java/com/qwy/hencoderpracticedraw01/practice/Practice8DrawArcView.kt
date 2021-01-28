package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class Practice8DrawArcView : View {


    private val paint1 = Paint()
    private val paint2 = Paint()


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {


        paint1.isAntiAlias = true

        paint2.isAntiAlias = true
        paint2.style = Paint.Style.STROKE


    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        /**
         * drawArc()是使用一个椭圆来描述弧形的。
         *
         * left,top,right,bottom描述的是这个弧形所在的椭圆;
         *
         * startAngle是弧形的起始角度(x轴的正向，即正右的方向，是0度位置;顺时针为正角度，逆时针为负角度),
         *
         * sweepAngle是弧形划过的角度;
         *
         * useCenter表示是否连接到圆心，
         * 如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
         */

        // 绘制扇形
        canvas?.drawArc(200f, 100f, 800f, 500f, -110f, 100f, true, paint1)

        // 绘制弧形
        canvas?.drawArc(200f, 100f, 800f, 500f, 20f, 140f, true, paint1)

        // 绘制不封口的弧形
        canvas?.drawArc(200f, 100f, 800f, 500f, 180f, 60f, false, paint2)

    }
}
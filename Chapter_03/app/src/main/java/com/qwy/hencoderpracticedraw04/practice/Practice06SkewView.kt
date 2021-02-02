package com.qwy.hencoderpracticedraw04.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Practice06SkewView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point1: Point = Point(200, 200)
    var point2: Point = Point(800, 200)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.skew(0f, 0.5f)
        bitmap?.let { canvas?.drawBitmap(it, point1.x.toFloat(), point1.y.toFloat(), paint) }
        canvas?.restore()

//        canvas?.save()
//        canvas?.skew(-0.5f, 0f)
        bitmap?.let { canvas?.drawBitmap(it, point2.x.toFloat(), point2.y.toFloat(), paint) }
//        canvas?.restore()
    }

}
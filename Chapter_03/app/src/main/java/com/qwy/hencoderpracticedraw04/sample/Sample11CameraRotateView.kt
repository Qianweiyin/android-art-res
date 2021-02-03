package com.qwy.hencoderpracticedraw04.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Sample11CameraRotateView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point1: Point = Point(200, 100)
    var point2: Point = Point(600, 200)
    var camera: Camera = Camera()

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


        canvas?.run {
            save()
            camera.save()
            camera.rotateX(30f)
            camera.applyToCanvas(canvas)
            camera.restore()
            bitmap?.let { drawBitmap(it, point1.x.toFloat(), point1.y.toFloat(), paint) }
            restore()
        }


    }

}
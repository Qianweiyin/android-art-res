package com.qwy.hencoderpracticedraw04.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.qwy.chapter_03.R

class Sample05RotateView : View {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    private val point1 = Point(200, 200)
    private val point2 = Point(600, 200)


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

        val bitmapWidth = bitmap!!.width
        val bitmapHeight = bitmap!!.height

        canvas?.run {
            Log.e(
                "QwyRotate", "bitmapWidth : ${(point1.x + bitmapWidth / 2).toFloat()} " +
                        " bitmapHeight : ${(point1.y + bitmapHeight / 2).toFloat()}"
            )

            save()
            rotate(
                180f,
                (point1.x + bitmapWidth / 2).toFloat(),
                (point1.y + bitmapHeight / 2).toFloat()
            )
            bitmap?.let {
                drawBitmap(it, point1.x.toFloat(), point1.y.toFloat(), paint)
            }
            restore()

            Log.e(
                "QwyRotate", "bitmapWidth point2 : ${(point2.x + bitmapWidth / 2).toFloat()} " +
                        " bitmapHeight : ${(point2.y + bitmapHeight / 2).toFloat()}"
            )
            save()
            rotate(
                45f,
                (point2.x + bitmapWidth / 2).toFloat(),
                (point2.y + bitmapHeight / 2).toFloat()
            )
            drawBitmap(bitmap!!, point2.x.toFloat(), point2.y.toFloat(), paint)
            restore()


        }


    }
}
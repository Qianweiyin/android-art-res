package com.qwy.hencoderpracticedraw04.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Sample09MatrixRotateView : View {


    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point1: Point = Point(200, 200)
    var point2: Point = Point(600, 200)
    var matrixRotate: Matrix = Matrix()

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

        canvas!!.save()
        matrixRotate.reset()
        matrixRotate.postRotate(
            180f, (point1.x + bitmapWidth / 2).toFloat(),
            (point1.y + bitmapHeight / 2).toFloat()
        )
        canvas.concat(matrixRotate)
        canvas.drawBitmap(bitmap!!, point1.x.toFloat(), point1.y.toFloat(), paint)
        canvas.restore()

        canvas.save()
        matrixRotate.reset()
        matrixRotate.postRotate(
            45f, (point2.x + bitmapWidth / 2).toFloat(),
            (point2.y + bitmapHeight / 2).toFloat()
        )
        canvas.concat(matrixRotate)
        canvas.drawBitmap(bitmap!!, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas.restore()

    }
}
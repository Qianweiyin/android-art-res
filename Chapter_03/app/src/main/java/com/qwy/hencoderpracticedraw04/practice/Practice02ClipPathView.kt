package com.qwy.hencoderpracticedraw04.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R

class Practice02ClipPathView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var path1: Path = Path()
    var path2: Path = Path()
    var point1: Point = Point(200, 200)
    var point2: Point = Point(600, 200)


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

        path1.addCircle(point1.x + 200f, point1.y + 200f, 150f, Path.Direction.CW)

        path2.fillType = Path.FillType.INVERSE_WINDING
        path2.addCircle(point2.x + 200f, point2.y + 200f, 150f, Path.Direction.CW)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.let {
            it.save()
            it.clipPath(path1)
            bitmap?.let { it1 ->
                it.drawBitmap(it1, point1.x.toFloat(), point1.y.toFloat(), paint)
            }
            it.restore()


            it.save()
            it.clipPath(path2)
            bitmap?.let { it1->
                it.drawBitmap(it1, point2.x.toFloat(), point2.y.toFloat(), paint)
            }
            it.restore()
        }

    }
}
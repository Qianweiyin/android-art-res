package com.qwy.hencoderpracticedraw04.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R

class Sample03TranslateView : View {


    val paint = Paint()
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


        canvas?.run {
            save()
            // 默认的绘图原点在(0,0),
            // 调用translate(x,y)后,则将原点移动到了(x,y).
            // 之后的所有绘图操作都将以(x,y)为原点执行。
            translate(-100f, -100f)

            //None of the following functions can be called with the arguments supplied.
            bitmap?.let { drawBitmap(it, point1.x.toFloat(), point1.y.toFloat(), paint) }
            restore()



            save()
            translate(200f, 0f)
            bitmap?.let { drawBitmap(it, point2.x.toFloat(), point2.y.toFloat(), paint) }
            restore()
        }

    }
}
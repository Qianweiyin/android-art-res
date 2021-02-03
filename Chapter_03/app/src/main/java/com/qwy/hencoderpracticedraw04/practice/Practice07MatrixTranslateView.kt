package com.qwy.hencoderpracticedraw04.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Practice07MatrixTranslateView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var point1: Point = Point(200, 200)
    var point2: Point = Point(600, 200)

    //Accidental override: The following declarations have the same JVM signature (getMatrix()Landroid/graphics/Matrix;):
    //public final fun <get-matrix>(): Matrix defined in com.qwy.hencoderpracticedraw04.sample.Sample07MatrixTranslateView
    //public open fun getMatrix(): Matrix! defined in com.qwy.hencoderpracticedraw04.sample.Sample07MatrixTranslateView
    private var matrixTranslate: Matrix = Matrix()

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
            matrixTranslate.reset()
            matrixTranslate.postTranslate(-100f, -100f)
            concat(matrixTranslate)
            bitmap?.let { drawBitmap(it, point1.x.toFloat(), point1.y.toFloat(), paint) }
            restore()




        }
    }

}
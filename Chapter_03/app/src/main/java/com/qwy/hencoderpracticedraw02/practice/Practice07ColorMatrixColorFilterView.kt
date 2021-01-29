package com.qwy.hencoderpracticedraw02.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R

class Practice07ColorMatrixColorFilterView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)

        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val colorFilter: ColorFilter = ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = colorFilter
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bitmap?.let { canvas?.drawBitmap(it, 0f, 0f, paint) }
    }
}

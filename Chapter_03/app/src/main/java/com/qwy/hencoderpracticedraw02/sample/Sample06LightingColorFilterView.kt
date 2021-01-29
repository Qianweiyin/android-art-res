package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Sample06LightingColorFilterView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var colorFilter1: ColorFilter = LightingColorFilter(0x00ffff, 0x000000)
    var colorFilter2: ColorFilter = LightingColorFilter(0xffffff, 0x003000)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.colorFilter = colorFilter1
        bitmap?.let { canvas?.drawBitmap(it, 0f, 0f, paint) }

        paint.colorFilter = colorFilter2
        bitmap?.let { canvas?.drawBitmap(it, bitmap!!.width + 100f, 0f, paint) }
    }
}
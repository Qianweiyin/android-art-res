package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Sample08XfermodeView : View {
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap1: Bitmap? = null
    var bitmap2: Bitmap? = null
    var xfermode1: Xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
    var xfermode2: Xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    var xfermode3: Xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.batman);
        bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.batman_logo);
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val saved = canvas?.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)

        bitmap1?.let { canvas?.drawBitmap(it, 0f, 0f, paint) }
        paint.xfermode = xfermode1
        bitmap2?.let { canvas?.drawBitmap(it, 0f, 0f, paint) }
        paint.xfermode = null

        bitmap1?.let { canvas?.drawBitmap(it, bitmap1!!.width + 100f, 0f, paint) }
        paint.xfermode = xfermode2
        bitmap2?.let { canvas?.drawBitmap(it, bitmap1!!.width + 100f, 0f, paint) }
        paint.xfermode = null

        bitmap1?.let { canvas?.drawBitmap(it, 0f, bitmap1!!.height + 20f, paint) }
        paint.xfermode = xfermode3
        bitmap2?.let { canvas?.drawBitmap(it, 0f, bitmap1!!.height + 20f, paint) }
        paint.xfermode = null

        if (saved != null) {
            canvas.restoreToCount(saved)
        }
    }
}
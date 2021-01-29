package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.qwy.chapter_03.R


class Sample14MaskFilterView : View {

    private var paint1: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paint2: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paint3: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var maskFilter1: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
    var maskFilter2: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.INNER)
    var maskFilter3: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.OUTER)
    var maskFilter4: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.SOLID)


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {


        setLayerType(LAYER_TYPE_SOFTWARE, null)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.what_the_fuck01)

        paint1.maskFilter = maskFilter1
        paint2.maskFilter = maskFilter2
        paint3.maskFilter = maskFilter3


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



        Log.e("QwyOnDraw", "isHardwareAccelerated  :${canvas?.isHardwareAccelerated}")

        bitmap?.let { canvas?.drawBitmap(it, 100f, 50f, paint1) }

        bitmap?.let { canvas?.drawBitmap(it, bitmap!!.width + 200f, 50f, paint2) }

        bitmap?.let { canvas?.drawBitmap(it, 100f, bitmap!!.height + 100f, paint3) }
//
//        bitmap?.let { canvas?.drawBitmap(it, bitmap!!.width + 200f, bitmap!!.height + 100f, paint) }
    }
}
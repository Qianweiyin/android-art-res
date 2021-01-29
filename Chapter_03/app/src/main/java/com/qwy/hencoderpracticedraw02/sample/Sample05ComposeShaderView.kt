package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class Sample05ComposeShaderView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.batman)
        val shader1: Shader = BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)
        val shader2: Shader = BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = ComposeShader(shader1, shader2, PorterDuff.Mode.DST_IN)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(200f, 200f, 200f, paint)
    }
}
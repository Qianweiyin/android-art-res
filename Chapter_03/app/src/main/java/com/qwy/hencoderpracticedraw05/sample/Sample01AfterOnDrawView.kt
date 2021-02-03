package com.qwy.hencoderpracticedraw05.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.qwy.chapter_03.R


class Sample01AfterOnDrawView : AppCompatImageView {


    companion object {
        const val DEBUG = true
    }

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {

        paint.color = Color.parseColor("#FFC107")
        paint.textSize = 28f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (DEBUG) {
            // 在 debug 模式下绘制出 drawable 的尺寸信息
            val drawable = drawable
            if (drawable != null) {
                canvas?.save()
                canvas?.concat(imageMatrix)
                val bounds: Rect = drawable.bounds
                canvas?.drawText(
                    resources.getString(
                        R.string.image_size,
                        bounds.width(),
                        bounds.height()
                    ), 20f, 40f, paint
                )
                canvas?.restore()
            }
        }
    }
}
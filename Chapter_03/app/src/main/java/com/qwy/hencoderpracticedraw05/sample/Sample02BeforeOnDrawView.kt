package com.qwy.hencoderpracticedraw05.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.Layout
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class Sample02BeforeOnDrawView : AppCompatTextView {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bounds = RectF()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        paint.color = Color.parseColor("#FFC107")
    }

    override fun onDraw(canvas: Canvas?) {

        // 在 super.onDraw() 绘制文字之前，先绘制出被强调的文字的背景
        val layout: Layout = layout
        bounds.left = layout.getLineLeft(1)
        bounds.right = layout.getLineRight(1)
        bounds.top = layout.getLineTop(1).toFloat()
        bounds.bottom = layout.getLineBottom(1).toFloat()
        canvas!!.drawRect(bounds, paint)
        bounds.left = layout.getLineLeft(layout.lineCount - 4)
        bounds.right = layout.getLineRight(layout.lineCount - 4)
        bounds.top = layout.getLineTop(layout.lineCount - 4).toFloat()
        bounds.bottom = layout.getLineBottom(layout.lineCount - 4).toFloat()
        canvas.drawRect(bounds, paint)


        super.onDraw(canvas)

    }
}
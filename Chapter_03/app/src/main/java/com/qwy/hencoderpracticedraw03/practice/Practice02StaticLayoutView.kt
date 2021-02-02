package com.qwy.hencoderpracticedraw03.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class Practice02StaticLayoutView : View {
    var textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    var text = "Hello HenCoder"
    var staticLayout: StaticLayout? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        textPaint.textSize = 60f
        // 这两行的位置不能换哟
        // 这两行的位置不能换哟
        staticLayout = StaticLayout(
            text,
            textPaint,
            600,
            Layout.Alignment.ALIGN_NORMAL,
            1f,
            0f,
            true
        )
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.translate(50f, 40f)
        staticLayout?.draw(canvas)
        canvas?.restore()
    }
}
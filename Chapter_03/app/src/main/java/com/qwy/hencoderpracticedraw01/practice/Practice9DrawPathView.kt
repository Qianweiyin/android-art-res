package com.qwy.hencoderpracticedraw01.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class Practice9DrawPathView : View {

    private val paint = Paint()
    private val path = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        paint.isAntiAlias = true

        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            path.addArc(200f, 200f, 400f, 400f, -225f, 225f)
            path.arcTo(400f, 200f, 600f, 400f, -180f, 225f, false)
        }
        path.lineTo(400f, 542f)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        // 绘制出 path 描述的图形（心形），大功告成
        canvas?.drawPath(path, paint)

    }
}
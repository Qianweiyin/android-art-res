package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Sample15FillPathView : View {


    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var pathPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var path: Path = Path()
    var path1: Path = Path()
    var path2: Path = Path()
    var path3: Path = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        path.moveTo(50f, 100f)
        path.rLineTo(50f, 100f)
        path.rLineTo(80f, -150f)
        path.rLineTo(100f, 100f)
        path.rLineTo(70f, -120f)
        path.rLineTo(150f, 80f)

        pathPaint.style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 使用 Paint.getFillPath() 获取实际绘制的 Path

        // 第一处：获取 Path
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 0f
        paint.getFillPath(path, path1)
        canvas?.drawPath(path, paint)

        canvas?.save()
        canvas?.translate(500F, 0F)
        canvas?.drawPath(path1, pathPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f, 200f)
        paint.style = Paint.Style.STROKE
        // 第二处：设置 Style 为 STROKE 后再获取 Path
        paint.getFillPath(path, path2)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(500f, 200f)
        canvas?.drawPath(path2, pathPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f, 400f)
        paint.strokeWidth = 40f
        // 第三处：Style 为 STROKE 并且线条宽度为 40 时的 Path
        paint.getFillPath(path, path3)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(500f, 400f)
        canvas?.drawPath(path3, pathPaint)
        canvas?.restore()
    }
}
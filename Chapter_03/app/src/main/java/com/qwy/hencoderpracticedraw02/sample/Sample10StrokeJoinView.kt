package com.qwy.hencoderpracticedraw02.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Sample10StrokeJoinView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var path: Path = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.strokeWidth = 40f
        paint.style = Paint.Style.STROKE

        path.rLineTo(200f, 0f)
        path.rLineTo(-160f, 120f)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()

        canvas?.translate(100f, 100f)
        paint.strokeJoin = Paint.Join.MITER
        canvas?.drawPath(path, paint)

        canvas?.translate(300f, 0f)
        paint.strokeJoin = Paint.Join.BEVEL
        canvas?.drawPath(path, paint)

        canvas?.translate(300f, 0f)
        paint.strokeJoin = Paint.Join.ROUND
        canvas?.drawPath(path, paint)

        canvas?.restore()
    }
}
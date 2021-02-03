package com.qwy.hencoderpracticedraw05.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.qwy.chapter_03.R


/**
 * 自定义View 1-5 绘制顺序
 *
 * Android里面的绘制都是按顺序的,先绘制的内容会被后绘制的盖住。
 *
 *  1 super.onDraw()前 or 后 ?
 *  1.1 写在super.onDraw()的下面
 *  1.2 写在super.onDraw()的上面
 *
 *  2 dispatchDraw() : 绘制子View的方法
 *  2.1 写在super.dispatchDraw()的下面
 *  2.2 写在super.dispatchDraw()的上面
 *
 *  3 绘制过程简述
 *  一个完整的绘制过程会依次绘制以下几个内容:
 *  1.背景
 *  2.主体(onDraw())
 *  3.子View(dispatchDraw())
 *  4.滑动边缘渐变和滑动条
 *  5.前景
 *
 *  4 onDrawForeground()
 *  4.1 写在super.onDrawForeground()的下面
 *  4.2 写在super.onDrawForeground()的上面
 *  4.3 想在滑动边缘渐变、滑动条和前景之间插入绘制代码?
 *
 *  5 draw() 总调度方法
 *  5.1 写在super.draw()的下面
 *  5.2 写在super.draw()的上面
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


class Practice01AfterOnDrawView : AppCompatImageView {


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
package com.qwy.hencoderpracticedraw03.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Sample11GetFontSpacingView : View {

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "Hello HenCoder"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        paint.textSize = 60f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        /**
         * 2.2  测量文字尺寸类
         *
         * 不论是文字，还是图形或Bitmap,只有知道了尺寸,才能更好地确定应该摆放的位置。
         * 由于文字的绘制和图形或Bitmap的绘制比起来,尺寸的计算复杂得多,
         * 所以它有一整套的方法来计算文字尺寸。
         *
         * 2.2.1 float getFontSpacing()    获取推荐的行距。
         *
         * 2.2.2 FontMetrics  getFontMetrics() 获取Paint 的 FontMetrics
         *
         * 2.2.3 getTextBounds(String text,int start,int end,Rect bounds) 获取文字的显示范围
         *
         * 2.2.4 float measureText(String text) 测量文字的宽度并返回
         *
         * 2.2.5 getTextWidths(String text,float[] widths)  获取字符串中每个字符的宽度,并把结果填入参数widths。
         *
         * 2.2.6 int breakText(String text,boolean measureForwards,float maxWidth,float[] measureWidth)
         *       测量文字宽度。但和measureText()的区别是，breakText()是在给出宽度上限的前提下测量文字的宽度。
         *       如果文字的宽度超出了上限，那么在临近超限的位置截断文字。
         *
         *
         */


        val spacing = paint.fontSpacing

        canvas?.drawText(text, 50f, 100f, paint)

        canvas?.drawText(text, 50f, 100f + spacing, paint)

        canvas?.drawText(text, 50f, 100f + spacing * 2, paint)

    }
}
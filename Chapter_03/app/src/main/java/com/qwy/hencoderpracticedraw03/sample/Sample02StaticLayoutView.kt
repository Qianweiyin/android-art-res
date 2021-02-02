package com.qwy.hencoderpracticedraw03.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class Sample02StaticLayoutView : View {
    var textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)


    var textLong = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
    var textWrap = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz"


    var text = "Hello HenCoder"
    var staticLayout: StaticLayout? = null


    var staticLayout2: StaticLayout? = null


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        textPaint.textSize = 60f

        //'constructor StaticLayout(CharSequence!, TextPaint!, Int, Layout.Alignment!, Float, Float, Boolean)' is deprecated. Deprecated in Java
        //This constructor is deprecated. Use Builder instead.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            staticLayout = StaticLayout.Builder.obtain(textLong, 0, textLong.length, textPaint, 800)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(0f, 1f)
                .setIncludePad(true)
                .build()

            staticLayout2 =
                StaticLayout.Builder.obtain(textWrap, 0, textWrap.length, textPaint, 600)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setLineSpacing(0f, 1f)
                    .setIncludePad(true)
                    .build()

        } else {
            staticLayout = StaticLayout(
                textLong,
                textPaint,
                600,
                Layout.Alignment.ALIGN_NORMAL,
                1f,
                0f,
                true
            )


            staticLayout2 = StaticLayout(
                textWrap,
                textPaint,
                600,
                Layout.Alignment.ALIGN_NORMAL,
                1f,
                0f,
                true
            )
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


//        canvas?.drawText(textLong, 50f, 300f, textPaint)
//        canvas?.drawText(textWrap, 50f, 450f, textPaint)

        /**
         * StaticLayout
         *
         * 这也是使用Canvas来进行文字的绘制，不过并不是使用Canvas的方法。
         *
         * Canvas.drawText()只能绘制单行的文字，而不能换行。
         * 它：
         *   1.不能在View的边缘自动拆行
         *    (到了View的边缘处，文字继续向后绘制到看不见的地方，而不是自动换行)
         *   2.不能在换行符\n处换行
         *   (在换行符\n的位置并没有换行，而只是加了空格)
         *
         *   如果需要绘制多行的文字，你必须自行把文字切断后分多次使用drawText()来绘制，或者使用StaticLayout。
         *
         *   StaticLayout并不是一个View或者ViewGroup,而是android.text.Layout的子类,它是纯粹用来绘制文字的。
         *   StaticLayout支持换行，它既可以为文字设置宽度上限来让文字自动换行,也会在\n处主动换行。
         *
         *   下面代码中出现的
         *   Canvas.save()
         *   Canvas.translate()
         *   Canvas.restore()
         *   配合起来可以对绘制的内容进行移动。
         *
         *  StaticLayout的构造方法是
         *  StaticLayout(CharSequence source,
         *               TextPaint paint,
         *               int width,
         *               Alignment align,
         *               float spacingmult,
         *               float spacingadd,
         *               boolean includepad),其中参数里：
         *
         *              width : 文字区域的宽度，文字到达整个宽度后就会自动换行;
         *              align : 是文字的对齐方向;
         *              spacingmult : 是行间距的倍数，通常情况下填1 就好;
         *              spacingadd  : 是行间距的额外增加值，通常情况下填0 就好;
         *              includepad  : 是指是否在文字上下添加额外的空间，
         *                            来避免某些过高的字符的绘制，出现越界。
         *
         *
         */

        canvas?.save()
        canvas?.translate(50f, 40f)
        staticLayout?.draw(canvas)
        canvas?.translate(0f, 200f)
        staticLayout2?.draw(canvas)
        canvas?.restore()
    }
}
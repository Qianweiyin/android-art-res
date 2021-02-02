package com.qwy.hencoderpracticedraw03.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View


class Sample04SetTypefaceView : View {


    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "yummy jpq"
    var typeface: Typeface? = null


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {


        paint.textSize = 60f

        typeface = Typeface.createFromAsset(getContext().assets, "Satisfy-Regular.ttf")


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**
         * 2 Paint 对文字绘制的辅助
         *
         * 2.1 设置显示效果类
         *
         * 2.1.1 setTextSize(float textSize) 设置字体大小
         *
         * 2.1.2 setTypeface(Typeface typeface) 设置字体
         *
         * 2.1.3 setFakeBoldText(boolean fakeBoldText) 是否使用伪粗体
         *
         * 2.1.4 setStrikeThruText(boolean strikeThruText)  是否加删除线
         *
         * 2.1.5 setUnderlineText(boolean underlineText) 是否加下划线
         *
         * 2.1.6 setTextSkewX(float skewX)  设置文字横向错切角度。其实就是文字倾斜度。
         *
         * 2.1.7 setTextScaleX(float scaleX) 设置文字横向方缩。也就是文字变胖变瘦。
         *
         * 2.1.8 setLetterSpacing(float letterSpacing) 设置字符间距。默认值是0
         *
         * 2.1.9 setFontFeatureSettings(String settings) 用CSS的font-feature-settings的方式来设置文字。
         *
         * 2.1.10  setTextAlign(Paint.Align align)  设置文字的对齐方式。
         *
         * 2.1.11  setTextLocale(Locale locale)
         *        /setTextLocales(Locales locales)  设置绘制所使用的的Locale。
         *
         * 2.1.12 setHinting(int mode)
         *
         * 2.1.13 setElegantTextHeight(boolean elegant)
         *
         * 2.1.14  setSubpixelText(boolean subpixelText)  是否开启次像素级的抗锯齿()
         *
         * 2.1.15 setLinearText(boolean linearText)
         *
         */


        /**
         * 设置字体。
         */
        paint.typeface = null
        paint.textScaleX = 1f
        canvas?.drawText(text, 50f, 100f, paint)

        paint.typeface = Typeface.SERIF
        paint.textScaleX = 0.8f
        canvas?.drawText(text, 50f, 200f, paint)

//        paint.typeface = typeface
        paint.textScaleX = 1.2f
        canvas?.drawText(text, 50f, 300f, paint)

        /**
         * 是否使用伪粗体
         */
        paint.typeface = null
        paint.isFakeBoldText = true
        canvas?.drawText(text, 50f, 400f, paint)

        /**
         * 是否增加删除线
         */
        paint.isFakeBoldText = false
        paint.isStrikeThruText = true // 是否增加删除线
        paint.isUnderlineText = true //是否加下划线
        paint.textSkewX = -0.5f  //文字倾斜度
        canvas?.drawText(text, 50f, 500f, paint)


        /**
         * CSS全称 Cascading Style Sheets,
         * 是网页开发用来设置页面各种元素的样式的。
         *
         *
         */


    }
}

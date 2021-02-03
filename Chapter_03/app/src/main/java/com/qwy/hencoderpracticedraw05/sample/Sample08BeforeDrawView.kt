package com.qwy.hencoderpracticedraw05.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class Sample08BeforeDrawView : AppCompatEditText {


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }

    override fun draw(canvas: Canvas?) {

        canvas?.drawColor(Color.parseColor("#66BB6A")); // 涂上绿色


        super.draw(canvas)

    }
}
package com.qwy.lagoucourse14.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.qwy.lagoucourse14.printEvent
import com.qwy.lagoucourse14.printParam

class MyScrollView : ScrollView {


    companion object {
        private const val TAG = "MyScrollView"
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )


    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        printEvent(TAG, "onTouchEvent", event)
        val intercepted = super.onInterceptTouchEvent(event)
        printParam(TAG, "MyScrollView intercepted is $intercepted")
        return intercepted
    }
}
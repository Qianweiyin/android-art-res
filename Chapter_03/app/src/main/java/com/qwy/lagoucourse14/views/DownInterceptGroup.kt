package com.qwy.lagoucourse14.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.qwy.lagoucourse14.printEvent

class DownInterceptGroup : FrameLayout {


    companion object {
        private const val TAG = "DownInterceptGroup"
    }


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        printEvent(TAG, "dispatchTouchEvent", event)

        return super.dispatchTouchEvent(event)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        printEvent(TAG, "onInterceptTouchEvent", ev)

        return super.onInterceptTouchEvent(ev)

    }

}
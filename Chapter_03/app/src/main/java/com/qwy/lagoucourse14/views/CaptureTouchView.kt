package com.qwy.lagoucourse14.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.qwy.lagoucourse14.printEvent
import com.qwy.lagoucourse14.printParam

class CaptureTouchView : View {


    companion object {
        private const val TAG = "CaptureTouchView"
    }


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {

        setOnTouchListener { _, event ->
            if (MotionEvent.ACTION_UP == event?.action) {
                performClick()
            }
            true
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        printEvent(TAG, "dispatchTouchEvent", event)
        val result = super.dispatchTouchEvent(event)
        printParam(TAG, "dispatchTouchEvent result is  $result")
        return result

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (MotionEvent.ACTION_UP == event?.action) {
            performClick()
        }
        printEvent(TAG, "onTouchEvent", event)
        return true
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(500, 300)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

}
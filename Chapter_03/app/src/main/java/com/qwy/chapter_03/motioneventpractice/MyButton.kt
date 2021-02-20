package com.qwy.chapter_03.motioneventpractice

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class MyButton : AppCompatButton {
    companion object {
        private const val TAG = "MyButton"
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "onTouchEvent ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "onTouchEvent ACTION_MOVE")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "onTouchEvent ACTION_UP")
                performClick()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        //Variable declaration could be inlined
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> Log.e(TAG, "dispatchTouchEvent ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> Log.e(TAG, "dispatchTouchEvent ACTION_MOVE")
            MotionEvent.ACTION_UP -> Log.e(TAG, "dispatchTouchEvent ACTION_UP")
        }
        return super.dispatchTouchEvent(event)
    }
}
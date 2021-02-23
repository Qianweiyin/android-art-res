package com.qwy.chapter_03.motioneventpractice

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * Android ViewGroup事件分发机制
 *
 * https://blog.csdn.net/lmj623565791/article/details/39102591
 *
 */
class MyLinearLayout : LinearLayout {


    companion object {
        private const val TAG = "MyLinearLayout"
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    /**
     * 用来进行事件的分发。
     * 如果事件能够传递给当前View，那么此方法一定会被调用，
     * 返回结果受当前View的onTouchEvent和下级dispatchTouchEvent方法的影响，
     * 表示是否消耗当前事件。
     */

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "dispatchTouchEvent  ACTION_DOWN")
            }

            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "dispatchTouchEvent  ACTION_MOVE")
            }

            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "dispatchTouchEvent  ACTION_UP")
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "onTouchEvent  ACTION_DOWN")
            }

            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "onTouchEvent  ACTION_MOVE")
            }

            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "onTouchEvent  ACTION_UP")
                performClick()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "onInterceptTouchEvent  ACTION_DOWN")
            }

            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "onInterceptTouchEvent  ACTION_MOVE")
            }

            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "onInterceptTouchEvent  ACTION_UP")
            }
        }
        return super.onInterceptTouchEvent(ev)
    }


    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        Log.e(TAG, "requestDisallowInterceptTouchEvent ")
        super.requestDisallowInterceptTouchEvent(disallowIntercept)
    }



    override fun performClick(): Boolean {
        return super.performClick()
    }
}
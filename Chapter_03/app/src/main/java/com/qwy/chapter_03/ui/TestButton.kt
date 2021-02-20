package com.qwy.chapter_03.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.appcompat.widget.AppCompatTextView
import com.nineoldandroids.view.ViewHelper
import com.qwy.chapter_03.motioneventpractice.MyButton

class TestButton : AppCompatTextView {

    companion object {
        private const val TAG = "QwyTestButton"
    }

    private var mScaledTouchSlop = 0

    // 分别记录上次滑动的坐标
    private var mLastX = 0
    private var mLastY = 0


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    private fun init() {
        mScaledTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        Log.d(TAG, "sts: $mScaledTouchSlop")
    }

    // TODO: 2021/2/7  performClick
    // Custom view TestButton overrides onTouchEvent but not performClick
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val x = event?.rawX?.toInt()
        val y = event?.rawY?.toInt()

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "onTouchEvent ACTION_DOWN")

            }

            MotionEvent.ACTION_MOVE -> {
                val deltaX = x?.minus(mLastX)
                val deltaY = y?.minus(mLastY)
//                Log.d(TAG, "move, deltaX: $deltaX deltaY: $deltaY")

                Log.e(TAG, "onTouchEvent ACTION_MOVE")


                val translationX = ViewHelper.getTranslationX(this).toInt() + deltaX!!
                val translationY = ViewHelper.getTranslationY(this).toInt() + deltaY!!
                ViewHelper.setTranslationX(this, translationX.toFloat())
                ViewHelper.setTranslationY(this, translationY.toFloat())

            }

            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "onTouchEvent ACTION_UP")

                performClick()
            }
        }
        if (x != null) {
            mLastX = x
        }
        if (y != null) {
            mLastY = y
        }

        return true

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
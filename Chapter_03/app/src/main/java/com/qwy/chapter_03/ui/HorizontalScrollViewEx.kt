package com.qwy.chapter_03.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class HorizontalScrollViewEx : ViewGroup {

    companion object {
        private const val TAG = "HorizontalScrollViewEx"
    }

    private lateinit var mScroller: Scroller
    private lateinit var mVelocityTracker: VelocityTracker


    private var mChildrenSize = 0
    private var mChildWidth = 0
    private var mChildIndex = 0

    // 分别记录上次滑动的坐标
    private var mLastX = 0
    private var mLastY = 0

    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private var mLastXIntercept = 0
    private var mLastYIntercept = 0


    constructor(context: Context?) : this(context, null) {
        init()
    }

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
        mScroller = Scroller(context)
        mVelocityTracker = VelocityTracker.obtain()

    }


    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        var intercepted = false
        val x = event.x.toInt()
        val y = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                    intercepted = true
                }
            }

            MotionEvent.ACTION_MOVE -> {
                //None of the following functions can be called with the arguments supplied.
                val deltaX = x - mLastXIntercept
                val deltaY = y - mLastYIntercept
                intercepted = abs(deltaX) > abs(deltaY)
            }
            MotionEvent.ACTION_UP -> {
                intercepted = false
            }
        }

        Log.d(TAG, "intercepted=$intercepted")
        mLastX = x
        mLastY = y
        mLastXIntercept = x
        mLastYIntercept = y

        return intercepted

    }

    //Custom view HorizontalScrollViewEx overrides onTouchEvent but not performClick
    override fun onTouchEvent(event: MotionEvent): Boolean {

        mVelocityTracker.addMovement(event)

        val x = event.x.toInt()
        val y = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - mLastX
                val deltaY = y - mLastY
                scrollBy(-deltaX, 0)
            }
            MotionEvent.ACTION_UP -> {
                val scrollX = scrollX
                val scrollToChildIndex = scrollX / mChildWidth
                mVelocityTracker.computeCurrentVelocity(1000)

                val xVelocity = mVelocityTracker.xVelocity

                mChildIndex = if (abs(xVelocity) >= 50) {
                    if (xVelocity > 0) mChildIndex - 1 else mChildIndex + 1
                } else {
                    (scrollX + mChildWidth / 2) / mChildWidth
                }

                mChildIndex = max(0, min(mChildIndex, mChildrenSize - 1))

                val dx = mChildIndex * mChildWidth - scrollX

                smoothScrollBy(dx, 0)
                mVelocityTracker.clear()
                performClick()
            }
        }

        mLastX = x
        mLastY = y

        return true

    }


    override fun performClick(): Boolean {
        return super.performClick()
    }

    private fun smoothScrollBy(dx: Int, dy: Int) {
        mScroller.startScroll(scrollX, 0, dx, 0, 500)
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var measuredWidth = 0
        var measuredHeight = 0
        val childCount = childCount
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)

        when {
            childCount == 0 -> {
                setMeasuredDimension(0, 0)
            }
            heightSpecMode == MeasureSpec.AT_MOST -> {
                val childView = getChildAt(0)
                measuredHeight = childView.measuredHeight
                setMeasuredDimension(widthSpaceSize, childView.measuredHeight)
            }
            widthSpecMode == MeasureSpec.AT_MOST -> {
                val childView = getChildAt(0)
                measuredWidth = childView.measuredWidth * childCount
                setMeasuredDimension(measuredWidth, heightSpecSize)
            }
            else -> {
                val childView = getChildAt(0)
                measuredWidth = childView.measuredWidth * childCount
                measuredHeight = childView.measuredHeight
                setMeasuredDimension(measuredWidth, measuredHeight)
            }
        }


    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0

        val childCount = childCount

        mChildrenSize = childCount

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView.visibility != GONE) {
                val childWidth = childView.measuredWidth
                mChildWidth = childWidth
                childView.layout(
                    childLeft, 0, childLeft + childWidth,
                    childView.measuredHeight
                )
                childLeft += childWidth
            }
        }

    }


    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
    }


    override fun onDetachedFromWindow() {
        mVelocityTracker.recycle()
        super.onDetachedFromWindow()
    }
}
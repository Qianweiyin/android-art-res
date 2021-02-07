package com.qwy.hencoderkeyboard03.like

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R
import com.qwy.hencoderpracticedraw06.dpToPixel


class LikeView : View {
    private val mPaint0 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mAnimator: ObjectAnimator? = null
    private var mCurNum = 9
    private var mNewNum = mCurNum
    private var translationY = 0
    private var liked = false
    private var mMoveY = 0
    private var mMaxLen = 0
    private var mTextSize = 0
    private var mTextPadding = 0
    private val mAnimTime = 500
    private var centerX = 0
    private var centerY = 0
    private var mUnlikeBitmap: Bitmap? = null
    private var mLikedBitmap: Bitmap? = null
    private var mShiningBitmap: Bitmap? = null


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {

        mMaxLen = 0
        mMoveY = dpToPixel(20f).toInt()
        mTextSize = dpToPixel(12f).toInt()
        mTextPadding = dpToPixel(25f).toInt()

        mPaint0.textSize = mTextSize.toFloat()
        mPaint1.textSize = mTextSize.toFloat()
        mPaint2.textSize = mTextSize.toFloat()
        mPaint0.color = Color.parseColor("#c3c4c3")
        mPaint1.color = Color.parseColor("#c3c4c3")
        mPaint2.color = Color.parseColor("#c3c4c3")

        mUnlikeBitmap =
            BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_unselected)
        mLikedBitmap =
            BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected)
        mShiningBitmap =
            BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected_shining)

        mAnimator = ObjectAnimator.ofInt(this, "translationY", 0, mMoveY)
        mAnimator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                mCurNum = mNewNum
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        mAnimator?.duration = mAnimTime.toLong()
        setOnClickListener {
            if (!(mAnimator?.isRunning)!!) {
                if (isLiked()) {
                    if (mCurNum != 0) {
                        mNewNum = mCurNum - 1
                    }
                } else {
                    mNewNum = mCurNum + 1
                }
                liked = !isLiked()
                mAnimator?.start()
            }
        }

    }


    fun clear() {
        mMaxLen = 0
    }

    private fun isLiked(): Boolean {
        return liked
    }

    fun setLike(isLike: Boolean) {
        if (liked) {
            setTranslationY(mMoveY.toFloat())
        } else {
            setTranslationY(0f)
        }
        liked = isLike
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mAnimator?.end()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        centerX = width / 2
        centerY = height / 2
        val leftX = centerX

        //绘制点赞图片

        //绘制点赞图片
        val likeTop = centerY - mUnlikeBitmap!!.height / 2
        val likeLeft = leftX - mTextPadding
        canvas?.let { drawLike(it, likeTop, likeLeft) }

        //绘制数字 相对居中

        //绘制数字 相对居中
        val rect = Rect()
        mPaint0.getTextBounds("0", 0, 1, rect)
        canvas?.let {
            drawNum(it, leftX, centerY - (rect.top + rect.bottom) / 2, mCurNum, mNewNum)
            //绘制闪光
            //绘制闪光
            drawShining(canvas, likeTop, likeLeft)
        }

    }


    private fun drawLike(canvas: Canvas, likeTop: Int, likeLeft: Int) {
        val mLike = liked
        if (mLike && getAnimPercent() > 0.9 && getAnimPercent() < 1) {
            canvas.save()
            canvas.translate(
                (likeLeft - mLikedBitmap!!.width * 0.05).toFloat(),
                (likeTop - mLikedBitmap!!.height * 0.05).toFloat()
            )
            canvas.scale(1.1f, 1.1f)
            mLikedBitmap?.let {
                canvas.drawBitmap(it, 0f, 0f, mPaint0)
            }
            canvas.restore()
        } else if (mLike && getAnimPercent() > 0.5) {
            if (mLike) mLikedBitmap else mUnlikeBitmap?.let {
                canvas.drawBitmap(
                    it,
                    likeLeft.toFloat(),
                    likeTop.toFloat(),
                    mPaint0
                )
            }
        } else {
            mUnlikeBitmap?.let {
                canvas.drawBitmap(it, likeLeft.toFloat(), likeTop.toFloat(), mPaint0)
            }
        }
    }


    private fun drawShining(canvas: Canvas, likeTop: Int, likeLeft: Int) {
        if (liked) {
            val scale: Float = getAnimPercent()
            val shiningTop = (likeTop - scale * mShiningBitmap!!.height / 2).toInt()
            val shiningLeft =
                (likeLeft + (mLikedBitmap!!.width - scale * mShiningBitmap!!.width) / 2).toInt()
            canvas.save()
            canvas.translate(shiningLeft.toFloat(), shiningTop.toFloat())
            canvas.scale(scale, scale)
            canvas.drawBitmap(mShiningBitmap!!, 0f, 0f, mPaint0)
            canvas.restore()
        }
    }


    private fun getAnimPercent(): Float {
        return 1.0f * translationY / mMoveY
    }


    private fun drawNum(canvas: Canvas, leftX: Int, baseTxtY: Int, curNum: Int, newNum: Int) {
        var leftX = leftX
        val curNumStr = curNum.toString()
        val newNumStr = newNum.toString()
        mMaxLen = curNumStr.length.coerceAtLeast(newNumStr.length).coerceAtLeast(mMaxLen)
        if (newNum < curNum) {
            var lengthChange = false
            if (newNumStr.length < curNumStr.length) {
                lengthChange = true
            }
            var sumLeft = leftX
            for (i in curNumStr.indices) {
                val len: Float =
                    if (i == 0) 0f else mPaint0.measureText(curNumStr.substring(i - 1, i))
                sumLeft += len.toInt()
                val curBitStr = curNumStr.substring(i, i + 1)
                var newBitStr = ""
                newBitStr = if (lengthChange && i == 0) {
                    " "
                } else if (lengthChange) {
                    newNumStr.substring(i - 1, i)
                } else {
                    newNumStr.substring(i, i + 1)
                }
                optDrawNum(canvas, sumLeft, baseTxtY, curBitStr, newBitStr, false)
            }
        } else if (newNum > curNum) {
            //长度发生变化
            var lengthChange = false
            if (newNumStr.length > curNumStr.length) {
                lengthChange = true
            }
            var sumLeft = leftX
            for (i in newNumStr.indices) {
                val len: Float =
                    if (i == 0) 0f else mPaint0.measureText(newNumStr.substring(i - 1, i))
                sumLeft += len.toInt()
                var curBitStr = ""
                curBitStr = if (lengthChange && i == 0) {
                    " "
                } else if (lengthChange) {
                    curNumStr.substring(i - 1, i)
                } else {
                    curNumStr.substring(i, i + 1)
                }
                val newBitStr = newNumStr.substring(i, i + 1)
                optDrawNum(canvas, sumLeft, baseTxtY, curBitStr, newBitStr, true)
            }
        } else if (newNum == curNum) {
            val emptyLen = mPaint0.measureText("0")
            //处理100-99 位移的情况
            leftX += ((mMaxLen - curNumStr.length) * emptyLen.toInt())

            var sumLeft = leftX
            for (i in curNumStr.indices) {
                val len: Float =
                    if (i == 0) 0f else mPaint0.measureText(curNumStr.substring(i - 1, i))
                sumLeft += len.toInt()
                val curBitStr = curNumStr.substring(i, i + 1)
                val newBitStr = curNumStr.substring(i, i + 1)
                optDrawNum(canvas, sumLeft, baseTxtY, curBitStr, newBitStr)
            }
        }
    }


    private fun optDrawNum(
        canvas: Canvas,
        leftX: Int,
        baseTxtY: Int,
        curNum: String,
        newNum: String
    ) {
        optDrawNum(canvas, leftX, baseTxtY, curNum, newNum, false)
    }

    private fun optDrawNum(
        canvas: Canvas,
        leftX: Int,
        baseTxtY: Int,
        curNum: String,
        newNum: String,
        upOrDown: Boolean
    ) {
        if (curNum == newNum) {
            canvas.drawText(curNum, leftX.toFloat(), baseTxtY.toFloat(), mPaint0)
            return
        }
        val alpha = ((1 - 1.0 * translationY / mMoveY) * 255).toInt()
        mPaint1.alpha = alpha
        mPaint2.alpha = 255 - alpha
        val newBaseY: Int
        val transY: Int
        if (upOrDown) {
            transY = -translationY
            newBaseY = baseTxtY + mMoveY
        } else { //down -1
            transY = translationY
            newBaseY = baseTxtY - mMoveY
        }
        canvas.drawText(curNum, leftX.toFloat(), (baseTxtY + transY).toFloat(), mPaint1)
        canvas.drawText(newNum, leftX.toFloat(), (newBaseY + transY).toFloat(), mPaint2)
    }

    fun setDrawNum(mDrawNum: Int) {
        mCurNum = mDrawNum
        mNewNum = mDrawNum
        invalidate()
    }

    fun setTranslationY(translationY: Int) {
        this.translationY = translationY
        invalidate()
    }


}
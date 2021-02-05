package com.qwy.hencoderpracticedraw07.sample.sample03

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.qwy.chapter_03.R


class Sample03OfObjectLayout : RelativeLayout {


    var view: Sample03OfObjectView? = null
    var animateBt: Button? = null


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        view = findViewById<View>(R.id.objectAnimatorView) as Sample03OfObjectView
        animateBt = findViewById<View>(R.id.animateBt) as Button

        animateBt?.setOnClickListener {
            val animator = ObjectAnimator.ofObject(
                view,
                "position",
                PointFEvaluator(),
                PointF(0f, 0f),
                PointF(1f, 1f)
            )
            animator.interpolator = LinearInterpolator()
            animator.duration = 1000
            animator.start()
        }
    }


    private class PointFEvaluator : TypeEvaluator<PointF> {
        var newPoint = PointF()
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val x = startValue.x + fraction * (endValue.x - startValue.x)
            val y = startValue.y + fraction * (endValue.y - startValue.y)
            newPoint.set(x, y)
            return newPoint
        }
    }

}
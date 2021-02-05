package com.qwy.hencoderpracticedraw07.practice.practice01

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.qwy.chapter_03.R


class Practice01ArgbEvaluatorLayout : RelativeLayout {
    var view: Practice01ArgbEvaluatorView? = null
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
        view = findViewById<View>(R.id.objectAnimatorView) as Practice01ArgbEvaluatorView
        animateBt = findViewById<View>(R.id.animateBt) as Button

        animateBt!!.setOnClickListener {
            val animator =
                ObjectAnimator.ofInt(view, "color", 0xffff0000.toInt(), 0xff00ff00.toInt())
            animator.setEvaluator(ArgbEvaluator())
            animator.interpolator = LinearInterpolator()
            animator.duration = 2000
            animator.start()
        }
    }
}
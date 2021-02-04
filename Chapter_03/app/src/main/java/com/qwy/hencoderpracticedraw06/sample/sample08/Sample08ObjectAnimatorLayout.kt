package com.qwy.hencoderpracticedraw06.sample.sample08

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.RelativeLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.qwy.chapter_03.R


class Sample08ObjectAnimatorLayout : RelativeLayout {

    var view: Sample08ObjectAnimatorView? = null
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
        view = findViewById<View>(R.id.objectAnimatorView) as Sample08ObjectAnimatorView
        animateBt = findViewById<View>(R.id.animateBt) as Button

        animateBt?.setOnClickListener(OnClickListener {
            val animator: ObjectAnimator =
                ObjectAnimator.ofFloat(view, "progress", 0f, 65f)

            animator.duration = 1000
            animator.interpolator = FastOutSlowInInterpolator()
            animator.start()
        })
    }

}
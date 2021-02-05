package com.qwy.hencoderpracticedraw07.practice.practice06

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.qwy.chapter_03.R


class Practice06KeyframeLayout : RelativeLayout {

    var view: Practice06KeyframeView? = null
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
        view = findViewById<View>(R.id.objectAnimatorView) as Practice06KeyframeView
        animateBt = findViewById<View>(R.id.animateBt) as Button

        animateBt!!.setOnClickListener {
            val keyframe1 = Keyframe.ofFloat(0f, 0f) // 开始：progress 为 0
            val keyframe2 = Keyframe.ofFloat(0.5f, 100f) // 进行到一半是，progress 为 100
            val keyframe3 = Keyframe.ofFloat(1f, 80f) // 结束时倒回到 80
            val holder =
                PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3)
            val animator = ObjectAnimator.ofPropertyValuesHolder(view, holder)
            animator.duration = 2000
            animator.interpolator = FastOutSlowInInterpolator()
            animator.start()
        }
    }
}
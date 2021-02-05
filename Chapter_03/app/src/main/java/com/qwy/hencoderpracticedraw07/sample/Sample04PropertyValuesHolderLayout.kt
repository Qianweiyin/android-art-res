package com.qwy.hencoderpracticedraw07.sample

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.RelativeLayout
import com.qwy.chapter_03.R


class Sample04PropertyValuesHolderLayout : RelativeLayout {

    var view: View? = null
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
        view = findViewById(R.id.objectAnimatorView)
        animateBt = findViewById<View>(R.id.animateBt) as Button

        animateBt?.setOnClickListener(OnClickListener {
            val holder1 = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f)
            val holder2 = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f)
            val holder3 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
            ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3).start()
        })
    }
}
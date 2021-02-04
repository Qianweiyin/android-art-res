package com.qwy.hencoderpracticedraw06.sample

import android.content.Context
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.qwy.chapter_03.R
import com.qwy.hencoderpracticedraw06.dpToPixel


class Sample07Interpolator : LinearLayout {
    var spinner: Spinner? = null
    var animateBt: Button? = null
    var imageView: ImageView? = null
    private var interpolators = arrayOfNulls<Interpolator>(13)
    var interpolatorPath: Path? = null


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {

        interpolatorPath = Path()
        interpolatorPath?.run {
            lineTo(0.25f, 0.25f)
            moveTo(0.25f, 1.5f)
            lineTo(1f, 1f)
        }
        //Variable expected
        interpolators[0] = AccelerateDecelerateInterpolator()
        interpolators[1] = LinearInterpolator()
        interpolators[2] = AccelerateInterpolator()
        interpolators[3] = DecelerateInterpolator()
        interpolators[4] = AnticipateInterpolator()
        interpolators[5] = OvershootInterpolator()
        interpolators[6] = AnticipateOvershootInterpolator()
        interpolators[7] = BounceInterpolator()
        interpolators[8] = CycleInterpolator(0.5f)
        interpolators[9] = PathInterpolatorCompat.create(interpolatorPath)
        interpolators[10] = FastOutLinearInInterpolator()
        interpolators[11] = FastOutSlowInInterpolator()
        interpolators[12] = LinearOutSlowInInterpolator()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        spinner = findViewById<View>(R.id.interpolatorSpinner) as Spinner

        animateBt = findViewById<View>(R.id.animateBt) as Button
        imageView = findViewById<View>(R.id.imageView) as ImageView
        animateBt!!.setOnClickListener {
            imageView!!.animate()
                .translationX(dpToPixel(150f))
                .setDuration(600)
                .setInterpolator(interpolators[spinner!!.selectedItemPosition])
                .withEndAction { imageView!!.postDelayed({ imageView!!.translationX = 0f }, 500) }
        }
    }
}
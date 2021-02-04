package com.qwy.hencoderpracticedraw06.sample

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import com.qwy.chapter_03.R
import com.qwy.hencoderpracticedraw06.dpToPixel


class Sample06Duration : LinearLayout {

    var durationSb: SeekBar? = null
    var durationValueTv: TextView? = null
    var animateBt: Button? = null
    var imageView: ImageView? = null

    var duration = 300
    var translationState = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        durationSb = findViewById<View>(R.id.durationSb) as SeekBar
        durationValueTv = findViewById<View>(R.id.durationValueTv) as TextView
        durationValueTv!!.text = context.getString(R.string.ms_with_value, duration)
        durationSb!!.max = 10
        durationSb!!.progress = 1
        durationSb!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                duration = progress * 300
                durationValueTv!!.text = context.getString(R.string.ms_with_value, duration)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        animateBt = findViewById<View>(R.id.animateBt) as Button
        imageView = findViewById<View>(R.id.imageView) as ImageView
        animateBt!!.setOnClickListener {
            when (translationState) {
                0 -> imageView!!.animate().translationX(dpToPixel(100f)).duration =
                    duration.toLong()
                1 -> imageView!!.animate().translationX(0f).duration =
                    duration.toLong()
            }
            if (translationState < 1) {
                translationState++
            } else {
                translationState = 0
            }
        }

    }

}
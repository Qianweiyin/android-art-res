package com.qwy.hencoderkeyboard03.like

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import com.qwy.chapter_03.R


class LikeLayout : RelativeLayout {

    var view: LikeView? = null
    var animateBt: Button? = null
    private var etNum: EditText? = null


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        view = findViewById<View>(R.id.objectAnimatorView) as LikeView
        etNum = findViewById<View>(R.id.editNum) as EditText
        animateBt = findViewById<View>(R.id.animateBt) as Button

        val listener = OnClickListener {
            view!!.setDrawNum(etNum!!.text.toString().toInt())
            view!!.clear()
        }
        animateBt!!.setOnClickListener(listener)

    }

}
package com.qwy.hencoderpracticedraw06.sample

import android.content.Context
import android.graphics.Outline
import android.graphics.Path
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.qwy.chapter_03.R
import com.qwy.hencoderpracticedraw06.dpToPixel


class Sample01Translation : RelativeLayout {

    var animateBt: Button? = null
    var imageView: ImageView? = null
    var translationStateCount = if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) 6 else 4
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
        animateBt = findViewById(R.id.animateBt)
        imageView = findViewById(R.id.imageView)



        if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            // 给音乐图标加上合适的阴影
            imageView?.outlineProvider = MusicOutlineProvider()
        }


        Log.e(
            "QwyView",
            "onAttachedToWindow left :  ${imageView?.left}  top:  ${imageView?.top}   translationX:  ${imageView?.translationX} "
        )

        animateBt?.setOnClickListener(OnClickListener {
            when (translationState) {
                0 -> {
                    imageView?.animate()?.translationX(dpToPixel(100f))

//                    Log.e(
//                        "QwyView",
//                        "dpToPixel  :  ${dpToPixel(100f)}"
//                    )

                    Log.e(
                        "QwyView",
                        "onClick left :  ${imageView?.left}  top:  ${imageView?.top} translationX:  ${imageView?.translationX} "
                    )
                }
                1 -> imageView?.animate()?.translationX(0f)
                2 -> imageView?.animate()?.translationY(dpToPixel(50f))
                3 -> imageView?.animate()?.translationY(0f)
                4 -> if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView?.animate()?.translationZ(dpToPixel(150f))
                }
                5 -> if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView?.animate()?.translationZ(0f)
                }
            }
            translationState++
            if (translationState == translationStateCount) {
                translationState = 0
            }
        })
    }

    /**
     * 为音乐图标设置三角形的 Outline。
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    internal class MusicOutlineProvider : ViewOutlineProvider() {
        var path: Path = Path()
        override fun getOutline(view: View?, outline: Outline) {
            outline.setConvexPath(path)
        }

        init {
            path.moveTo(0f, dpToPixel(10f))
            path.lineTo(dpToPixel(7f), dpToPixel(2f))
            path.lineTo(dpToPixel(116f), dpToPixel(58f))
            path.lineTo(dpToPixel(116f), dpToPixel(70f))
            path.lineTo(dpToPixel(7f), dpToPixel(128f))
            path.lineTo(0f, dpToPixel(120f))
            path.close()
        }
    }

}
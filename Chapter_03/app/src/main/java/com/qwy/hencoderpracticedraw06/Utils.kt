package com.qwy.hencoderpracticedraw06

import android.content.res.Resources
import android.util.DisplayMetrics


fun dpToPixel(dp: Float): Float {
    val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
    return dp * metrics.density
}
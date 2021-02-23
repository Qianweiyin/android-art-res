package com.qwy.lagoucourse14

import android.util.Log
import android.view.MotionEvent


fun printEvent(tag: String?, methodName: String, event: MotionEvent?) {
    when (event?.action) {
        MotionEvent.ACTION_DOWN -> Log.e(tag, "  $methodName:ACTION_DOWN")
        MotionEvent.ACTION_MOVE -> Log.e(tag, "  $methodName:ACTION_MOVE")
        MotionEvent.ACTION_UP -> Log.e(tag, "  $methodName:ACTION_UP")
        MotionEvent.ACTION_CANCEL -> Log.e(tag, "   $methodName:ACTION_CANCEL")
    }
}

fun printParam(tag: String?, reg: String?) {
    Log.i(tag, reg!!)
}
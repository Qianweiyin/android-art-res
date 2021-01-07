package com.qwy.chapter_02

import android.app.ActivityManager
import android.content.Context


fun getProcessName(cxt: Context, pid: Int): String? {
    val am = cxt
        .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningApps = am.runningAppProcesses ?: return null
    for (procInfo in runningApps) {
        if (procInfo.pid == pid) {
            return procInfo.processName
        }
    }
    return null
}
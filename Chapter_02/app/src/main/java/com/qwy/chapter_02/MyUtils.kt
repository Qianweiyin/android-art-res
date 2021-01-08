package com.qwy.chapter_02

import android.app.ActivityManager
import android.content.Context
import android.os.Environment
import java.io.Closeable


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


fun close(closeable: Closeable?) {
    closeable?.close()
}

// 本地文件目录结构
val ALBUM_PATH = Environment
    .getExternalStorageDirectory().toString()

val CHAPTER_2_PATH: String = ALBUM_PATH + "/qianweiyin"

val CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache"

const val MSG_FROM_CLIENT = 0
const val MSG_FROM_SERVICE = 1
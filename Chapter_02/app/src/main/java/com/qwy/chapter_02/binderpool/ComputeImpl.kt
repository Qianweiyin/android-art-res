package com.qwy.chapter_02.binderpool

import com.ICompute

/**
 * 业务模块的AIDL接口定义和实现
 */
class ComputeImpl : ICompute.Stub() {
    override fun add(a: Int, b: Int): Int {
        return a + b
    }
}
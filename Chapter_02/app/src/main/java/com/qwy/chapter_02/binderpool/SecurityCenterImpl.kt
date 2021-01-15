package com.qwy.chapter_02.binderpool

import android.os.RemoteException
import com.ISecurityCenter

/**
 * 业务模块的AIDL接口定义和实现
 */
class SecurityCenterImpl : ISecurityCenter.Stub() {

    companion object {

        private const val SECRET_CODE = '^'
    }

    //Unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
    //public inline infix fun BigInteger.xor(other: BigInteger): BigInteger defined in kotlin
    @Throws(RemoteException::class)
    override fun encrypt(content: String): String {
        val chars = content.toCharArray()
        for (i in chars.indices) {
            chars[i] = ((chars[i].toInt() xor SECRET_CODE.toInt()).toChar())
        }
        return String(chars)
    }

    @Throws(RemoteException::class)
    override fun decrypt(password: String): String {
        return encrypt(password)
    }


}
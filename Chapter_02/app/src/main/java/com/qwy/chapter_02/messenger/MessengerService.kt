package com.qwy.chapter_02.messenger

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.qwy.chapter_02.MSG_FROM_CLIENT
import com.qwy.chapter_02.MSG_FROM_SERVICE


class MessengerService : Service() {

    private val mMessenger = Messenger(MessengerHandler())


    override fun onBind(intent: Intent): IBinder? {
        return mMessenger.binder
    }

    companion object {
        private const val TAG = "QwyMessenger"

        class MessengerHandler : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    MSG_FROM_CLIENT -> {
                        Log.e(TAG, "receive msg from Client: ${msg.data.getString("msg")}")
                        val client = msg.replyTo
                        val replyMessage = Message.obtain(null, MSG_FROM_SERVICE)
                        val bundle = Bundle()
                        bundle.putString("reply", "嗯，你的消息我已经收到，稍后会回复你。")
                        replyMessage.data = bundle
                        client.send(replyMessage)
                    }
                    else -> super.handleMessage(msg)
                }
            }
        }
    }
}
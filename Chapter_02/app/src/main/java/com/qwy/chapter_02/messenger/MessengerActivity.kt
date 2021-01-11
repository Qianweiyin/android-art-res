package com.qwy.chapter_02.messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_02.MSG_FROM_CLIENT
import com.qwy.chapter_02.R


class MessengerActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "QwyMessenger"
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val mService = Messenger(service)
            Log.d(TAG, "bind service")
            val msg: Message = Message.obtain(null, MSG_FROM_CLIENT)
            val data = Bundle()
            data.putString("msg", "hello, this is client.")
            msg.data = data
            mService.send(msg)
        }

        override fun onServiceDisconnected(className: ComponentName) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        val intent = Intent(this, MessengerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        unbindService(mConnection)
        super.onDestroy()
    }
}


////    private val mGetReplyMessenger: Messenger = Messenger(MessengerHandler())
//
//private class MessengerHandler : Handler() {
//    override fun handleMessage(msg: Message) {
//        when (msg.what) {
//            MSG_FROM_SERVICE -> Log.i(
//                MessengerActivity.TAG,
//                "receive msg from Service:" + msg.getData().getString("reply")
//            )
//            else -> super.handleMessage(msg)
//        }
//    }
//}

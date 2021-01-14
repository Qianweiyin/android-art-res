package com.qwy.chapter_02.socket

import android.content.Intent
import android.os.*
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_02.R
import com.qwy.chapter_02.close
import java.io.*
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread


class TCPClientActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val MESSAGE_RECEIVE_NEW_MSG = 1
        private val MESSAGE_SOCKET_CONNECTED = 2
        private val MODIFY = 3
    }

    //    private val mSendButton: Button? = null
    private lateinit var mSendButton: Button
    private var mMessageTextView: TextView? = null
    private var mMessageEditText: EditText? = null

    private var mPrintWriter: PrintWriter? = null
    private var mClientSocket: Socket? = null

    // Do not concatenate text displayed with setText. Use resource string with placeholders.
    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_RECEIVE_NEW_MSG -> {
                    mMessageTextView?.text = mMessageTextView?.text.toString() + msg.obj as String
                }
                MESSAGE_SOCKET_CONNECTED -> {
                    mSendButton.isEnabled = true
                }
                MODIFY -> {
                    mMessageEditText?.setText("")
                    val time = formatDateTime(System.currentTimeMillis())
                    val showedMsg = "\nself $time:${msg.obj as String}\n"
                    mMessageTextView!!.text = mMessageTextView!!.text.toString() + showedMsg
                }
                else -> {
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcpclient)
        mMessageTextView = findViewById<View>(R.id.msg_container) as TextView
        mSendButton = findViewById<View>(R.id.send) as Button
        mSendButton.setOnClickListener(this)
        mMessageEditText = findViewById<View>(R.id.msg) as EditText
        val service = Intent(this, TCPServerService::class.java)
        startService(service)

        thread {
            connectTCPServer()
        }

    }


    override fun onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket?.shutdownInput()
                mClientSocket?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        thread {
            if (v === mSendButton) {
                val msg = mMessageEditText?.text.toString()
                if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                    mPrintWriter?.println(msg)
                    val message = mHandler.obtainMessage()
                    message.what = MODIFY
                    message.obj = msg
                    mHandler.sendMessage(message)
                }
            }
        }
    }


    private fun connectTCPServer() {
        var socket: Socket? = null
        while (socket == null) {
            try {
                socket = Socket("localhost", 8688)
                mClientSocket = socket
                mPrintWriter =
                    PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED)
                println("connect server success")
            } catch (e: IOException) {
                SystemClock.sleep(1000)

                //failed to connect to localhost/127.0.0.1 (port 8688) from /:: (port 47401): connect failed: ECONNREFUSED (Connection refused)
                println("connect tcp server failed, retry..." + e.message)
            }
        }
        try {
            // 接收服务器端的消息
            val br = BufferedReader(InputStreamReader(socket.getInputStream()))
            while (!this@TCPClientActivity.isFinishing) {
                val msg: String = br.readLine()
                println("receive :$msg")
                val time = formatDateTime(System.currentTimeMillis())
                val showedMsg = """server $time:$msg""".trimIndent()
                mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget()
            }
            println("quit...")
            close(mPrintWriter)
            close(br)
            socket.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // To get local formatting use getDateInstance(), getDateTimeInstance(), or getTimeInstance(),
    // or use new SimpleDateFormat(String template, Locale locale) with for example Locale.US for ASCII dates.
    private fun formatDateTime(time: Long): String? {
        return SimpleDateFormat("(HH:mm:ss)").format(Date(time))
    }
}
package com.qwy.chapter_02.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.qwy.chapter_02.close
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class TCPServerService : Service() {


    private var mIsServiceDestroyed = false

    private val mDefinedMessages = arrayOf(
        "你好啊，哈哈",
        "请问你叫什么名字呀？",
        "今天北京天气不错啊，shy",
        "你知道吗？我可是可以和多个人同时聊天的哦",
        "给你讲个笑话吧：据说爱笑的人运气不会太差，不知道真假。"
    )


    override fun onCreate() {
        Thread(TcpServer()).start()

        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        mIsServiceDestroyed = true
        super.onDestroy()
    }


    private inner class TcpServer : Runnable {
        override fun run() {
            val serverSocket: ServerSocket?
            try {
                //监听本地8688端口
                serverSocket = ServerSocket(8688)
                while (!mIsServiceDestroyed) {
                    // 接受客户端请求
                    val client: Socket = serverSocket.accept()
                    println("accept")
                    thread {
                        responseClient(client)
                    }
                }
            } catch (e: IOException) {
                System.err.println("establish tcp server failed,port:8688")
                e.printStackTrace()
                return
            }
        }
    }

    @Throws(IOException::class)
    private fun responseClient(client: Socket) {
        // 用于接收客户端消息
        val bufferedWriter = BufferedReader(InputStreamReader(client.getInputStream()))
        // 用于向客户端发送消息
        val out = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream())), true)
        out.println("欢迎来到聊天室！")
        while (!mIsServiceDestroyed) {

            //bufferedWriter.readLine() must not be null
            val str: String = bufferedWriter.readLine()
            println("msg from client:$str")
            if (str == null) {
                //客户端断开连接
                break
            }
            val i: Int = Random().nextInt(mDefinedMessages.size)
            val msg = mDefinedMessages[i]
            out.println(msg)
            println("send :$msg")
        }
        println("client quit.")
        // 关闭流
        close(out)
        close(bufferedWriter)
        client.close()
    }

}
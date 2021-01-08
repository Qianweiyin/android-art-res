package com.qwy.chapter_02

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.qwy.chapter_02.manager.UserManager
import com.qwy.chapter_02.model.UserSerializable
import java.io.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UserManager.sUserId = 2

        findViewById<Button>(R.id.button1)
            .setOnClickListener {
                val intent = Intent()
                intent.setClass(this, SecondActivity::class.java)

                var userSerializable = UserSerializable(0, "jake", true)

                intent.putExtra("extra_user", userSerializable as Serializable?)

                startActivity(intent)
            }

    }

    override fun onResume() {
        Log.e(TAG, "UserManage.sUserId = ${UserManager.sUserId}")
        persistToFile()
        super.onResume()

    }

    private fun persistToFile() {
//        object : Thread(Runnable {
//        })
//            .start()


//        thread {  }

        Thread(Runnable {
            val user = UserSerializable(1, "hello world", false)
            val dir = File(CHAPTER_2_PATH)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val cachedFile = File(CACHE_FILE_PATH)
            var objectOutputStream: ObjectOutputStream? = null
            try {
                objectOutputStream = ObjectOutputStream(
                    FileOutputStream(cachedFile)
                )
                objectOutputStream.writeObject(user)
                Log.d(TAG, "persist user:$user")
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                close(objectOutputStream)
            }
        }).start()


    }
}
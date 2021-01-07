package com.qwy.chapter_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.qwy.chapter_02.manager.UserManager

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
                    startActivity(intent)
                }

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "UserManage.sUserId = ${UserManager.sUserId}")

    }
}
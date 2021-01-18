package com.qwy.chapter_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button1).setOnClickListener {

            val intent = Intent()
            intent.setClass(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }


    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

}
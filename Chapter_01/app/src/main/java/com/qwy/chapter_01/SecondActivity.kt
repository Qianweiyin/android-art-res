package com.qwy.chapter_01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SecondActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second);
        Log.e(TAG,"onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
    }


    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }


}
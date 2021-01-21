package com.qwy.chapter_01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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


        val test: String? = savedInstanceState?.getString("extra_test")
        Log.e(TAG, "[onCreate]restore extra_test: $test")


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState")
        outState.putString("extra_test", "text")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val test = savedInstanceState.getString("extra_test")
        Log.e(TAG, "[onRestoreInstanceState]restore extra_test:$test")

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
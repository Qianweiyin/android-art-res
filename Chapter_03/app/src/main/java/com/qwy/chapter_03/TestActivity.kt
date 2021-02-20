package com.qwy.chapter_03

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_03.motioneventpractice.MyButton


class TestActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {


    companion object {
        private const val TAG = "TestActivity"
        private const val TAG2 = "QwyTestButton"
        private const val MESSAGE_SCROLL_TO = 1
        private const val FRAME_COUNT = 30
        private const val DELAYED_TIME = 33
    }


    private lateinit var mButton1: Button
    private lateinit var mButton2: View
    private val mCount: Int = 0


    private lateinit var myButton: MyButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initView()

    }

    private fun initView() {
        mButton1 = findViewById<View>(R.id.button1) as Button
        mButton1.setOnClickListener(this)
        mButton2 = findViewById<View>(R.id.button2) as TextView

        myButton = findViewById<View>(R.id.myButton) as MyButton
        // onTouch lambda should call View#performClick when a click is detected
        mButton2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> Log.e(TAG2, "onTouch ACTION_DOWN")
                MotionEvent.ACTION_MOVE -> Log.e(TAG2, "onTouch ACTION_MOVE")
                MotionEvent.ACTION_UP -> {
                    mButton2.performClick()
                    Log.e(TAG2, "onTouch ACTION_UP")
                }
            }
            false
        }

        myButton.setOnClickListener(this)

//        myButton.setOnClickListener {
//            Toast.makeText(this, "onclick", Toast.LENGTH_SHORT).show()
//        }
        myButton.setOnLongClickListener(this)


    }

    override fun onClick(v: View?) {
        Toast.makeText(
            applicationContext,
            "onclick",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onLongClick(v: View?): Boolean {
//        Toast.makeText(this, "long click", Toast.LENGTH_SHORT).show()
        Toast.makeText(
            applicationContext,
            "setOnLongClickListener",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }
}
package com.qwy.chapter_02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    //logt
    companion object {
        private const val TAG = "SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<Button>(R.id.button1)
            .setOnClickListener {
                val intent = Intent()
                intent.setClass(this, ThirdActivity::class.java)
                startActivity(intent)
            }
    }


}
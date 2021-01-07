package com.qwy.chapter_02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ThirdActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third);

    }

}
package com.qwy.lagoucourse14

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_03.R

class LaGouTouchMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lagou_14_touch_main_activity)
    }


    fun cancelEvent(view: View) {
        val intent = Intent(this, CancelEventActivity::class.java)
        startActivity(intent)
    }

    fun intercept(view: View) {
        val intent = Intent(this, InterceptActivity::class.java)
        startActivity(intent)
    }


}
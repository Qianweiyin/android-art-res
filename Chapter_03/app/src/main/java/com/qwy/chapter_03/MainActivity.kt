package com.qwy.chapter_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.qwy.hencoderpracticedraw01.PracticeDraw01Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onDestroy() {

        //这一些操作
        super.onDestroy()
        //
    }

    // Corresponding method handler 'public void onClickView01(android.view.View)' not found
    fun onClickView01(view: View) {
        val intent = Intent(this, PracticeDraw01Activity::class.java)
        startActivity(intent)
    }

    fun onClickView02(view: View) {
        val intent = Intent(this, PracticeDraw01Activity::class.java)
        startActivity(intent)
    }
}
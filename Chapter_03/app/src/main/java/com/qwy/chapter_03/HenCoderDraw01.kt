package com.qwy.chapter_03

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qwy.hencoderkeyboard03.KeyBoard03Activity
import com.qwy.hencoderpracticedraw01.PracticeDraw01Activity
import com.qwy.hencoderpracticedraw02.PracticeDraw02Activity
import com.qwy.hencoderpracticedraw03.PracticeDraw03Activity
import com.qwy.hencoderpracticedraw04.PracticeDraw04Activity
import com.qwy.hencoderpracticedraw05.PracticeDraw05Activity
import com.qwy.hencoderpracticedraw06.PracticeDraw06Activity
import com.qwy.hencoderpracticedraw07.PracticeDraw07Activity

class HenCoderDraw01 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hen_coder_draw01)

    }


    // Corresponding method handler 'public void onClickView01(android.view.View)' not found
    fun onClickView01(view: View) {
        val intent = Intent(this, PracticeDraw01Activity::class.java)
        startActivity(intent)
    }

    fun onClickView02(view: View) {
        val intent = Intent(this, PracticeDraw02Activity::class.java)
        startActivity(intent)
    }

    fun onClickView03(view: View) {
        val intent = Intent(this, PracticeDraw03Activity::class.java)
        startActivity(intent)
    }

    fun onClickView04(view: View) {
        val intent = Intent(this, PracticeDraw04Activity::class.java)
        startActivity(intent)
    }

    fun onClickView05(view: View) {
        val intent = Intent(this, PracticeDraw05Activity::class.java)
        startActivity(intent)
    }

    fun onClickView06(view: View) {
        val intent = Intent(this, PracticeDraw06Activity::class.java)
        startActivity(intent)
    }

    fun onClickView07(view: View) {
        val intent = Intent(this, PracticeDraw07Activity::class.java)
        startActivity(intent)
    }

    fun onClickView08(view: View) {
        val intent = Intent(this, KeyBoard03Activity::class.java)
        startActivity(intent)
    }
}
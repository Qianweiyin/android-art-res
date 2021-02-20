package com.qwy.chapter_03

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


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

    /**
     * 绘制
     *
     * 自定义 View 1-1 绘制基础
     */
    fun henCoderDraw01(view: View) {
        val intent = Intent(this, HenCoderDraw01::class.java)
        startActivity(intent)
    }


    fun onButtonClick(v: View) {
        when (v.id) {
            //View基础
            R.id.button1 -> {
                val intent = Intent(this, TestActivity::class.java)
                startActivity(intent)
            }
            //滑动冲突场景1-外部拦截
            R.id.button2 -> {
                val intent = Intent(this, DemoActivity01::class.java)
                startActivity(intent)
            }
            //滑动冲突场景1-内部拦截
            R.id.button3 -> {
//                val intent = Intent(this, DemoActivity_2::class.java)
//                startActivity(intent)
            }
        }
    }

}
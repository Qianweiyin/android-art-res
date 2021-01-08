package com.qwy.chapter_02

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.qwy.chapter_02.model.UserSerializable

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


    override fun onResume() {
        super.onResume()
        val user: UserSerializable? = intent.getSerializableExtra("extra_user") as UserSerializable?
        Log.e(TAG, "user: ${user.toString()}")
    }


}
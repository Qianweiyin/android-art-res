package com.qwy.chapter_02.provider

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_02.R

class ProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)
        val uri: Uri = Uri.parse("content://com.qwy.chapter_02.provider")


//        This Cursor should be freed up after use with #close()
        contentResolver.query(uri, null, null, null, null)
        contentResolver.query(uri, null, null, null, null)
        contentResolver.query(uri, null, null, null, null)
    }

}
package com.qwy.chapter_03

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_03.ui.HorizontalScrollViewEx
import java.util.ArrayList

class DemoActivity01 : AppCompatActivity() {

    companion object {
        private const val TAG = "DemoActivity01"
    }

    private var mListContainer: HorizontalScrollViewEx? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_1)
        Log.d(TAG, "onCreate")
        initView()
    }

    private fun initView() {
        val inflater = layoutInflater
        mListContainer = findViewById<View>(R.id.container) as HorizontalScrollViewEx
        val screenWidth = MyUtils.getScreenMetrics(this).widthPixels
        val screenHeight = MyUtils.getScreenMetrics(this).heightPixels
        for (i in 0..2) {
            val layout = inflater.inflate(
                R.layout.content_layout, mListContainer, false
            ) as ViewGroup
            layout.layoutParams.width = screenWidth
            val textView = layout.findViewById<View>(R.id.title) as TextView
            // Do not concatenate text displayed with setText. Use resource string with placeholders.
            textView.text = "page ${i + 1}"
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0))
            createList(layout)
            mListContainer!!.addView(layout)
        }
    }

    private fun createList(layout: ViewGroup) {
        val listView = layout.findViewById<View>(R.id.list) as ListView
        val dates = ArrayList<String>()
        for (i in 0..49) {
            dates.add("name $i")
        }
        val adapter = ArrayAdapter(
            this,
            R.layout.content_list_item, R.id.name, dates
        )
        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(
                    this, "click item $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
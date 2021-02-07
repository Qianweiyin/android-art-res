package com.qwy.hencoderkeyboard03

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.qwy.chapter_03.R

class KeyBoard03Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.like_layout, R.string.title_like
        ),
    )

    class PageModel(
        @LayoutRes val sampleLayoutRes: Int,
        @StringRes val titleRes: Int,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practice_draw_01)
        pager = findViewById<View>(R.id.pager) as ViewPager
        tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout

        pager?.adapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.newInstance(
                    pageModel.sampleLayoutRes
                )
            }

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }
        }
        tabLayout?.setupWithViewPager(pager)
    }
}
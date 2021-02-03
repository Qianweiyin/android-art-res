package com.qwy.hencoderpracticedraw05

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
import com.qwy.hencoderpracticedraw01.PageFragment

class PracticeDraw05Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.sample_after_on_draw,
            R.string.title_after_on_draw,
            R.layout.practice_after_on_draw
        ),
        PageModel(
            R.layout.sample_before_on_draw,
            R.string.title_before_on_draw,
            R.layout.practice_before_on_draw
        ),
        PageModel(
            R.layout.sample_on_draw_layout,
            R.string.title_on_draw_layout,
            R.layout.practice_on_draw_layout
        ),
        PageModel(
            R.layout.sample_dispatch_draw,
            R.string.title_dispatch_draw,
            R.layout.practice_dispatch_draw
        ),
        PageModel(
            R.layout.sample_after_on_draw_foreground,
            R.string.title_after_on_draw_foreground,
            R.layout.practice_after_on_draw_foreground
        ),
        PageModel(
            R.layout.sample_before_on_draw_foreground,
            R.string.title_before_on_draw_foreground,
            R.layout.practice_before_on_draw_foreground
        ),
        PageModel(
            R.layout.sample_after_draw,
            R.string.title_after_draw,
            R.layout.practice_after_draw
        ),
        PageModel(
            R.layout.sample_before_draw,
            R.string.title_before_draw,
            R.layout.practice_before_draw
        ),
    )

    class PageModel(
        @LayoutRes val sampleLayoutRes: Int,
        @StringRes val titleRes: Int,
        @LayoutRes val practiceLayoutRes: Int
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
                    pageModel.sampleLayoutRes,
                    pageModel.practiceLayoutRes
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
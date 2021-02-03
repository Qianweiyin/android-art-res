package com.qwy.hencoderpracticedraw04

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

class PracticeDraw04Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.sample_clip_rect,
            R.string.title_clip_rect,
            R.layout.practice_clip_rect
        ),
        PageModel(
            R.layout.sample_clip_path,
            R.string.title_clip_path,
            R.layout.practice_clip_path
        ),
        PageModel(
            R.layout.sample_translate,
            R.string.title_translate,
            R.layout.practice_translate
        ),
        PageModel(
            R.layout.sample_scale,
            R.string.title_scale,
            R.layout.practice_scale
        ),
        PageModel(
            R.layout.sample_rotate,
            R.string.title_rotate,
            R.layout.practice_rotate
        ),
        PageModel(
            R.layout.sample_skew,
            R.string.title_skew,
            R.layout.practice_skew
        ),
        PageModel(
            R.layout.sample_matrix_translate,
            R.string.title_matrix_translate,
            R.layout.practice_matrix_translate
        ),
        PageModel(
            R.layout.sample_matrix_scale,
            R.string.title_matrix_scale,
            R.layout.practice_matrix_scale
        ),
        PageModel(
            R.layout.sample_matrix_rotate,
            R.string.title_matrix_rotate,
            R.layout.practice_matrix_rotate
        ),
        PageModel(
            R.layout.sample_matrix_skew,
            R.string.title_matrix_skew,
            R.layout.practice_matrix_skew
        ),PageModel(
            R.layout.sample_camera_rotate,
            R.string.title_camera_rotate,
            R.layout.practice_camera_rotate
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
package com.qwy.hencoderpracticedraw07

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

class PracticeDraw07Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.sample_argb_evaluator,
            R.string.title_argb_evaluator,
            R.layout.practice_argb_evaluator
        ),
        PageModel(
            R.layout.sample_hsv_evaluator,
            R.string.title_hsv_evaluator,
            R.layout.practice_hsv_evaluator
        ),
        PageModel(
            R.layout.sample_of_object,
            R.string.title_of_object,
            R.layout.practice_of_object
        ),
        PageModel(
            R.layout.sample_property_values_holder,
            R.string.title_property_values_holder,
            R.layout.practice_property_values_holder
        ),
        PageModel(
            R.layout.sample_animator_set,
            R.string.title_animator_set,
            R.layout.practice_animator_set
        ),
        PageModel(
            R.layout.sample_keyframe,
            R.string.title_keyframe,
            R.layout.practice_keyframe
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
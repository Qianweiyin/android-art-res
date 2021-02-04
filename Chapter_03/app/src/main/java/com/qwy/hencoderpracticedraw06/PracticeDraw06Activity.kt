package com.qwy.hencoderpracticedraw06

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

class PracticeDraw06Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.sample_translation,
            R.string.title_translation,
            R.layout.practice_translation
        ),
        PageModel(
            R.layout.sample_rotation,
            R.string.title_rotation,
            R.layout.practice_rotation
        ),
        PageModel(
            R.layout.sample_scale_06,
            R.string.title_scale_06,
            R.layout.practice_scale_06
        ),
        PageModel(
            R.layout.sample_alpha,
            R.string.title_alpha,
            R.layout.practice_alpha
        ),
        PageModel(
            R.layout.sample_multi_properties,
            R.string.title_multi_properties,
            R.layout.practice_multi_properties
        ),
        PageModel(
            R.layout.sample_duration,
            R.string.title_duration,
            R.layout.practice_duration
        ),
        PageModel(
            R.layout.sample_interpolator,
            R.string.title_interpolator,
            R.layout.practice_interpolator
        ),
        PageModel(
            R.layout.sample_object_anomator,
            R.string.title_object_animator,
            R.layout.practice_object_animator
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
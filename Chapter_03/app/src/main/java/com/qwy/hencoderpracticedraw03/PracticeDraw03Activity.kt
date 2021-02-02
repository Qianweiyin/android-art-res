package com.qwy.hencoderpracticedraw03

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

class PracticeDraw03Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.sample_draw_text,
            R.string.title_draw_text,
            R.layout.practice_draw_text
        ),
        PageModel(
            R.layout.sample_static_layout,
            R.string.title_static_layout,
            R.layout.practice_static_layout
        ),
        PageModel(
            R.layout.sample_set_text_size,
            R.string.title_set_text_size,
            R.layout.practice_set_text_size
        ),
        PageModel(
            R.layout.sample_set_typeface,
            R.string.title_set_typeface,
            R.layout.practice_set_typeface
        ),
//        PageModel(
//            R.layout.sample_set_fake_bold_text,
//            R.string.title_set_fake_bold_text,
//            R.layout.practice_set_fake_bold_text
//        ),
//
//        PageModel(
//            R.layout.sample_set_strike_thru_text,
//            R.string.title_set_strike_thru_text,
//            R.layout.practice_set_strike_thru_text
//        ),
//        PageModel(
//            R.layout.sample_set_underline_text,
//            R.string.title_set_underline_text,
//            R.layout.practice_set_underline_text
//        ),
//        PageModel(
//            R.layout.sample_set_text_skew_x,
//            R.string.title_set_text_skew_x,
//            R.layout.practice_set_text_skew_x
//        ),
//        PageModel(
//            R.layout.sample_set_text_scale_x,
//            R.string.title_set_text_scale_x,
//            R.layout.practice_set_text_scale_x
//        ),
        PageModel(
            R.layout.sample_set_text_align,
            R.string.title_set_text_align,
            R.layout.practice_set_text_align
        ),
        PageModel(
            R.layout.sample_get_font_spacing,
            R.string.title_get_font_spacing,
            R.layout.practice_get_font_spacing
        ),
        PageModel(
            R.layout.sample_measure_text,
            R.string.title_measure_text,
            R.layout.practice_measure_text
        ),
//        PageModel(
//            R.layout.sample_get_text_bounds,
//            R.string.title_get_text_bounds,
//            R.layout.practice_get_text_bounds
//        ),
        PageModel(
            R.layout.sample_get_font_metrics,
            R.string.title_get_font_metrics,
            R.layout.practice_get_font_metrics
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

            override fun getPageTitle(position: Int): CharSequence? {
                return getString(pageModels[position].titleRes)
            }
        }
        tabLayout?.setupWithViewPager(pager)
    }
}
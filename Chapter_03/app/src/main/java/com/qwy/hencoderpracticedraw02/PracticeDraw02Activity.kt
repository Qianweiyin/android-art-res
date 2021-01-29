package com.qwy.hencoderpracticedraw02

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

class PracticeDraw02Activity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null

    var pageModels: MutableList<PageModel> = arrayListOf(
        PageModel(
            R.layout.sample_linear_gradient,
            R.string.title_linear_gradient,
            R.layout.practice_linear_gradient
        ),
        PageModel(
            R.layout.sample_radial_gradient,
            R.string.title_radial_gradient,
            R.layout.practice_radial_gradient
        ),
        PageModel(
            R.layout.sample_sweep_gradient,
            R.string.title_sweep_gradient,
            R.layout.practice_sweep_gradient
        ),
        PageModel(
            R.layout.sample_bitmap_shader,
            R.string.title_bitmap_shader,
            R.layout.practice_bitmap_shader
        ),
        PageModel(
            R.layout.sample_compose_shader,
            R.string.title_compose_shader,
            R.layout.practice_compose_shader
        ),
        PageModel(
            R.layout.sample_lighting_color_filter,
            R.string.title_lighting_color_filter,
            R.layout.practice_lighting_color_filter
        ),
        PageModel(
            R.layout.sample_color_mask_color_filter,
            R.string.title_color_matrix_color_filter,
            R.layout.practice_color_matrix_color_filter
        ),
        PageModel(R.layout.sample_xfermode, R.string.title_xfermode, R.layout.practice_xfermode),
        PageModel(
            R.layout.sample_stroke_cap,
            R.string.title_stroke_cap,
            R.layout.practice_stroke_cap
        ),
        PageModel(
            R.layout.sample_stroke_join,
            R.string.title_stroke_join,
            R.layout.practice_stroke_join
        ),
        PageModel(
            R.layout.sample_stroke_miter,
            R.string.title_stroke_miter,
            R.layout.practice_stroke_miter
        ),
        PageModel(
            R.layout.sample_path_effect,
            R.string.title_path_effect,
            R.layout.practice_path_effect
        ),
        PageModel(
            R.layout.sample_shadow_layer,
            R.string.title_shader_layer,
            R.layout.practice_shadow_layer
        ),
        PageModel(
            R.layout.sample_mask_filter,
            R.string.title_mask_filter,
            R.layout.practice_mask_filter
        ),
        PageModel(R.layout.sample_fill_path, R.string.title_fill_path, R.layout.practice_fill_path),
        PageModel(R.layout.sample_text_path, R.string.title_text_path, R.layout.practice_text_path),
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
package com.qwy.hencoderkeyboard03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.qwy.chapter_03.R


class PageFragment : Fragment() {

    @LayoutRes
    var sampleLayoutRes = 0

    @LayoutRes
    var practiceLayoutRes = 0


    companion object{
        fun newInstance(
            @LayoutRes sampleLayoutRes: Int,
        ): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("sampleLayoutRes", sampleLayoutRes)
            fragment.arguments = args
            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_page, container, false)

        val sampleStub = view.findViewById<View>(R.id.sampleStub) as ViewStub
        sampleStub.layoutResource = sampleLayoutRes
        sampleStub.inflate()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes")
            practiceLayoutRes = args.getInt("practiceLayoutRes")
        }
    }

}
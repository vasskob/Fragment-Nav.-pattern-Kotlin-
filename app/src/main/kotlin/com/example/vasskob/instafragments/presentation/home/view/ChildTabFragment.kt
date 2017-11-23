package com.example.vasskob.instafragments.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vasskob.instafragments.R
import com.example.vasskob.instafragments.presentation.common.BaseFragment
import com.example.vasskob.instafragments.presentation.home.adapter.ChildFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragent_child_tab.*

class ChildTabFragment : BaseFragment() {

    companion object {
        fun newInstance(title: String, instance: Int): ChildTabFragment {
            val fragment = ChildTabFragment()
            val args = Bundle()
            args.putString(ARGS_TITLE, title)
            args.putInt(ARGS_INSTANCE, instance)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragent_child_tab, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initChildViewPager()
    }

    private fun initChildViewPager() {
        val mAdapter = ChildFragmentPagerAdapter(childFragmentManager, activity)
        vp_home_main.adapter = mAdapter
        vp_home_main.overScrollMode = (View.OVER_SCROLL_NEVER)
        sliding_tabs.setupWithViewPager(vp_home_main)
    }
}



package com.example.vasskob.fragmentnawpattern.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vasskob.fragmentnawpattern.R
import com.example.vasskob.fragmentnawpattern.presentation.common.BaseFragment
import com.example.vasskob.fragmentnawpattern.presentation.home.adapter.ChildFragmentPagerAdapter
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.fragent_child_tab.*
import timber.log.Timber

class ChildTabFragment : BaseFragment(), OnTabSelectListener {

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

    override fun onStart() {
        super.onStart()
        initChildViewPager()
//      hideActionBar()
    }

    private fun initChildViewPager() {
        vp_child_fragment.adapter = ChildFragmentPagerAdapter(childFragmentManager)
        stl_child_nav_bar.setViewPager(vp_child_fragment)
        stl_child_nav_bar.setOnTabSelectListener(this)
    }

    override fun onTabSelect(position: Int) {
        Timber.d(" onTabSelect")
    }

    override fun onTabReselect(position: Int) {
        Timber.d(" onTabReselect")
    }
}
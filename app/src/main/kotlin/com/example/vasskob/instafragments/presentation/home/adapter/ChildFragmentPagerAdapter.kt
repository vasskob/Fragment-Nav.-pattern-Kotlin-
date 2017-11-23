package com.example.vasskob.instafragments.presentation.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vasskob.instafragments.R
import com.example.vasskob.instafragments.presentation.home.view.RootFragment

class ChildFragmentPagerAdapter(fm: FragmentManager?, mContext: FragmentActivity?) : FragmentPagerAdapter(fm) {

    companion object {
        const val PAGE_FOLLOWING = 0
        const val PAGE_YOU = 1
    }

    private val firstChildTab = mContext?.getString(R.string.first_child_tab)
    private val secondChildTab = mContext?.getString(R.string.second_child_tab)

    private val mTabTitles: Array<String?> = arrayOf(firstChildTab, secondChildTab)

    override fun getItem(position: Int): Fragment = when (position) {
        PAGE_FOLLOWING -> RootFragment.newInstance(firstChildTab.toString(), 0)
        PAGE_YOU -> RootFragment.newInstance(secondChildTab.toString(), 0)
        else -> RootFragment.newInstance(firstChildTab.toString(), 0)
    }

    override fun getCount(): Int = mTabTitles.size

    override fun getPageTitle(position: Int): CharSequence = mTabTitles[position].toString()

}

package com.example.vasskob.fragmentnawpattern.presentation.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vasskob.fragmentnawpattern.presentation.home.view.RootFragment

class ChildFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    companion object {
        private const val FOLLOWING_TAB = "FOLLOWING"
        private const val YOU_TAB = "YOU"
        val mTabTitles: Array<String> = arrayOf(FOLLOWING_TAB, YOU_TAB)
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> RootFragment.newInstance(FOLLOWING_TAB, 0)
        1 -> RootFragment.newInstance(YOU_TAB, 0)
        else -> RootFragment.newInstance(FOLLOWING_TAB, 0)
    }

    override fun getCount(): Int = mTabTitles.size

    override fun getPageTitle(position: Int): CharSequence? = mTabTitles[position]
}

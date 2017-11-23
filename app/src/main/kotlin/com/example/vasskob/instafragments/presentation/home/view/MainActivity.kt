package com.example.vasskob.instafragments.presentation.home.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.vasskob.instafragments.R
import com.example.vasskob.instafragments.domain.model.TabModel
import com.example.vasskob.instafragments.presentation.common.BaseFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.FragNavTabHistoryController
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity(),
        OnTabSelectListener,
        FragNavSwitchController,
        FragNavController.RootFragmentListener,
        BaseFragment.FragmentNavigation {

    companion object {
        const val INDEX_FIRST = FragNavController.TAB1
        const val INDEX_SECOND = FragNavController.TAB2
        const val INDEX_THIRD = FragNavController.TAB3
        const val TAB_COUNT = 3
    }

    private lateinit var firstTabTitle: String
    private lateinit var secondTabTitle: String
    private lateinit var thirdTabTitle: String
    private lateinit var mNavController: FragNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabs()
        initNavController(savedInstanceState)
        restoreTabPosition(savedInstanceState)
    }

    private fun initTabs() {
        initTabTitles()
        val tabs = ArrayList<CustomTabEntity>()
        tabs.add(TabModel(firstTabTitle, R.drawable.ic_home_sel, R.drawable.ic_home))
        tabs.add(TabModel(secondTabTitle, R.drawable.ic_heart_sel, R.drawable.ic_heart))
        tabs.add(TabModel(thirdTabTitle, R.drawable.ic_camera_sel, R.drawable.ic_camera))
        tl_nav_bar.setTabData(tabs)
        tl_nav_bar.setOnTabSelectListener(this)
    }

    private fun initTabTitles() {
        firstTabTitle = getString(R.string.first_tab_title)
        secondTabTitle = getString(R.string.second_tab_title)
        thirdTabTitle = getString(R.string.third_tab_title)
    }

    override fun onTabSelect(position: Int) {
        Timber.d(" onTabSelect")
        mNavController.switchTab(position)
    }

    override fun onTabReselect(position: Int) {
        mNavController.clearStack()
    }

    private fun initNavController(savedInstanceState: Bundle?) {
        mNavController = FragNavController
                .newBuilder(savedInstanceState, supportFragmentManager, R.id.fragment_container)
                .rootFragmentListener(this, TAB_COUNT)
                .popStrategy(FragNavTabHistoryController.UNIQUE_TAB_HISTORY)
                .switchController(this)
                .build()
    }

    private fun restoreTabPosition(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            tl_nav_bar.currentTab = INDEX_FIRST
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            mNavController.onSaveInstanceState(outState)
        }
    }

    override fun onBackPressed() {
        if (!mNavController.popFragment()) {
            super.onBackPressed()
        }
    }

    override fun getRootFragment(p0: Int): Fragment = when (p0) {
        INDEX_FIRST -> RootFragment.newInstance(getPositionTitle(tl_nav_bar.currentTab), 0)
        INDEX_SECOND -> ChildTabFragment.newInstance(getPositionTitle(tl_nav_bar.currentTab), 0)
        INDEX_THIRD -> RootFragment.newInstance(getPositionTitle(tl_nav_bar.currentTab), 0)
        else -> throw IllegalStateException("Need to send an index that we know")
    }

    private fun getPositionTitle(position: Int): String = when (position) {
        INDEX_FIRST -> firstTabTitle
        INDEX_SECOND -> secondTabTitle
        INDEX_THIRD -> thirdTabTitle
        else -> firstTabTitle
    }

    override fun pushFragment(fragment: Fragment) {
        mNavController.pushFragment(fragment)
    }

    override fun switchTab(p0: Int, p1: FragNavTransactionOptions?) {
        tl_nav_bar.currentTab = p0
        onTabSelect(p0)
        Timber.d(" switchTab = $p0 FrTrans = $p1")
    }
}



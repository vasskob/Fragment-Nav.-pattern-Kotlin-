package com.example.vasskob.fragmentnawpattern

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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
        FragNavController.TransactionListener,
        FragNavController.RootFragmentListener,
        BaseFragment.FragmentNavigation {

    private var mNavController: FragNavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabs()
        initNavController(savedInstanceState)
    }

    private fun initTabs() {
        val tabs = ArrayList<CustomTabEntity>()
        tabs.add(TabEntity(FIRST, R.drawable.ic_camera_sel, R.drawable.ic_camera))
        tabs.add(TabEntity(SECOND, R.drawable.ic_taxi_sel, R.drawable.ic_taxi))
        tabs.add(TabEntity(THIRD, R.drawable.ic_laptop_sel, R.drawable.ic_laptop))
        tl_nav_bar.setTabData(tabs)

        tl_nav_bar.setOnTabSelectListener(this)
    }

    override fun onTabReselect(position: Int) {
        mNavController?.clearStack()
    }

    override fun onTabSelect(position: Int) {
        mNavController?.switchTab(position)
    }

    private fun initNavController(savedInstanceState: Bundle?) {
        mNavController = FragNavController
                .newBuilder(savedInstanceState, supportFragmentManager, R.id.fragment_container)
                .transactionListener(this)
                .rootFragmentListener(this, 3)
                .popStrategy(FragNavTabHistoryController.UNIQUE_TAB_HISTORY)
                .switchController(this)
                .build()
    }

    override fun onBackPressed() {
        if (!mNavController?.popFragment()!!) {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mNavController != null && outState != null) {
            mNavController!!.onSaveInstanceState(outState)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId

        if (id == android.R.id.home) {
            mNavController?.popFragment()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val FIRST = "First"
        const val SECOND = "Second"
        const val THIRD = "Third"
        const val INDEX_FIRST = FragNavController.TAB1
        const val INDEX_SECOND = FragNavController.TAB2
        const val INDEX_THIRD = FragNavController.TAB3
    }

    override fun onFragmentTransaction(p0: Fragment?, p1: FragNavController.TransactionType?) {
        if (supportActionBar != null && mNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!mNavController!!.isRootFragment)
        }
    }

    override fun onTabTransaction(p0: Fragment?, p1: Int) {
        if (supportActionBar != null && mNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!mNavController!!.isRootFragment)
        }
    }

    override fun getRootFragment(p0: Int): Fragment = when (p0) {
        INDEX_FIRST -> RootFragment.newInstance(getPositionTitle(tl_nav_bar.currentTab), 0)
        INDEX_SECOND -> RootFragment.newInstance(getPositionTitle(tl_nav_bar.currentTab), 0)
        INDEX_THIRD -> RootFragment.newInstance(getPositionTitle(tl_nav_bar.currentTab), 0)
        else -> throw IllegalStateException("Need to send an index that we know")
    }

    private fun getPositionTitle(position: Int): String = when (position) {
        0 -> FIRST
        1 -> SECOND
        2 -> THIRD
        else -> FIRST
    }

    override fun pushFragment(fragment: Fragment) {
        mNavController?.pushFragment(fragment)
    }

    override fun switchTab(p0: Int, p1: FragNavTransactionOptions?) {
        tl_nav_bar.currentTab = p0
        Timber.d(" switchTab = " + p0)
    }
}



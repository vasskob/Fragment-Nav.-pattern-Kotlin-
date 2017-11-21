package com.example.vasskob.fragmentnawpattern

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.vasskob.fragmentnawpattern.extension.activity.addFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity(), OnTabSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabs()
        openFragment(0)
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
        Timber.d("onTabReselect position ", position)
    }

    override fun onTabSelect(position: Int) {
        openFragment(position)
    }

    private fun openFragment(position: Int) {
        Timber.d("Open Fragment pos = " + position)
        val title = getPositionTitle(position)
        val fragment1: Fragment = RootFragment.newInstance(title)
        addFragment(fragment1, R.id.fragment_container)
    }

    private fun getPositionTitle(position: Int): String = when (position) {
        0 -> FIRST
        1 -> SECOND
        2 -> THIRD
        else -> FIRST
    }

    companion object {
        const val FIRST = "First"
        const val SECOND = "Second"
        const val THIRD = "Third"
    }
}


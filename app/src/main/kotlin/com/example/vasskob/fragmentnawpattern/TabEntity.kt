package com.example.vasskob.fragmentnawpattern

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity(private val title: String, private val selectedIcon: Int, private val unselectedIcon: Int) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int = unselectedIcon

    override fun getTabSelectedIcon(): Int = selectedIcon

    override fun getTabTitle(): String = title

}
package com.example.vasskob.instafragments.domain.model

import com.flyco.tablayout.listener.CustomTabEntity

class TabModel(private val title: String, private val selectedIcon: Int, private val unselectedIcon: Int) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int = unselectedIcon

    override fun getTabSelectedIcon(): Int = selectedIcon

    override fun getTabTitle(): String = title

}
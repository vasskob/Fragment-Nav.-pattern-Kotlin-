package com.example.vasskob.fragmentnawpattern.extension.activity

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.vasskob.fragmentnawpattern.extension.fragment.inTransaction

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}
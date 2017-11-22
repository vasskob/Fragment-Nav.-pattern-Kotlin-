package com.example.vasskob.fragmentnawpattern

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class BaseFragment : Fragment() {

    companion object {
        const val ARGS_INSTANCE = "args instance"
        const val ARGS_TITLE = "args title"
    }

    var mInstance: Int = 0
    var mTitle: String = ""

    lateinit var mFragmentNavigation: FragmentNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mTitle = args.getString(ARGS_TITLE)
            mInstance = args.getInt(ARGS_INSTANCE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_root, container, false)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = context
        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
    }
}
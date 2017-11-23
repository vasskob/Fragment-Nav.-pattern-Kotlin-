package com.example.vasskob.instafragments.presentation.home.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.vasskob.instafragments.R
import com.example.vasskob.instafragments.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_root.*
import java.util.*

class RootFragment : BaseFragment() {

    companion object {

        fun newInstance(title: String, instance: Int): RootFragment {
            val fragment = RootFragment()
            val args = Bundle()
            args.putString(ARGS_TITLE, title)
            args.putInt(ARGS_INSTANCE, instance)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_btn_back.setOnClickListener({
            mFragmentNavigation.onBackPressed()
        })
    }

    override fun onStart() {
        super.onStart()

        first_fr_container.setBackgroundColor(getRandomColor())
        bt_new_fragment.setOnClickListener({
            mFragmentNavigation.pushFragment(RootFragment.newInstance(mTitle, mInstance + 1))
        })
        tv_title.text = String.format(getString(R.string.root_fr_title), mTitle, mInstance)
        makeBackBtnVisible(mInstance != 0)
    }

    private fun getRandomColor(): Int {
        val r = Random()
        return Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256))
    }

    private fun makeBackBtnVisible(isVisible: Boolean) {
        if (isVisible) iv_btn_back.visibility = View.VISIBLE
        else iv_btn_back.visibility = View.INVISIBLE
    }
}
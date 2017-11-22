package com.example.vasskob.fragmentnawpattern

import android.graphics.Color
import android.os.Bundle
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

    override fun onStart() {
        super.onStart()

        first_fr_container.setBackgroundColor(getRandomColor())
        bt_new_fragment.setOnClickListener({
            mFragmentNavigation.pushFragment(RootFragment.newInstance(mTitle, mInstance + 1))
        })
        tv_title.text = String.format(getString(R.string.root_fr_title), mTitle, mInstance)
    }

    private fun getRandomColor(): Int {
        val r = Random()
        return Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256))
    }
}
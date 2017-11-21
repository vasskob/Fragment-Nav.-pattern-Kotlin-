package com.example.vasskob.fragmentnawpattern

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*
import java.util.*

class RootFragment : Fragment() {

    private var title: String = ""

    companion object {
        private const val TITLE_KEY = "title"

        fun newInstance(title: String): RootFragment {
            val fragment = RootFragment()
            val args = Bundle()
            args.putString(TITLE_KEY, title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments.getString(TITLE_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_first, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        first_fr_container.setBackgroundColor(getRandomColor())
        tv_title.text = String.format(getString(R.string.first_root_fr_title), title)
    }

    private fun getRandomColor(): Int {
        val r = Random()
        return Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256))
    }
}
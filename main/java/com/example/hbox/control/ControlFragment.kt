package com.example.hbox.control

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.hbox.R
import com.google.android.material.tabs.TabLayout

class ControlFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_control, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = MyAdapter(childFragmentManager)

        val tabStrip = view.findViewById<TabLayout>(R.id.pagerTabStrip)
        tabStrip.setupWithViewPager(viewPager)

        return view
    }

    class MyAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var tabList: List<Fragment> = listOf(ManuallyFragment(), AutomaticFragment())

        override fun getCount(): Int = tabList.size

        override fun getItem(position: Int): Fragment = tabList[position]


        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Manuell"
                else -> "Automatisch"
            }
        }
    }
}

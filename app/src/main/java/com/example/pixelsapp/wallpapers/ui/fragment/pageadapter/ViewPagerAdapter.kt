package com.example.pixelsapp.wallpapers.ui.fragment.pageadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(conteiner: FragmentActivity, private val fragmentList: List<Fragment>) :
    FragmentStateAdapter() {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]


}
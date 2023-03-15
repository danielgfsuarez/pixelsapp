package com.example.pixelsapp.wallpapers.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.pixelsapp.R
import com.example.pixelsapp.databinding.FragmentMainBinding
import com.example.pixelsapp.databinding.FragmentPopularBinding
import com.example.pixelsapp.wallpapers.ui.fragment.categories.CategoriesFragment
import com.example.pixelsapp.wallpapers.ui.fragment.collections.CollectionsFragment
import com.example.pixelsapp.wallpapers.ui.fragment.pageadapter.ViewPagerAdapter
import com.example.pixelsapp.wallpapers.ui.fragment.popular.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val tabTitle = listOf("Popular", "Collections", "Categories")
    private val fragments = listOf(PopularFragment(), CollectionsFragment(), CategoriesFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initViewPager()
        initTabLayout()

    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun initToolbar() {
        binding.toolbar.title = "Wallpapers"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager() {
        val pagerAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)

        binding.run {
            viewPager.adapter = pagerAdapter
        }
    }

}
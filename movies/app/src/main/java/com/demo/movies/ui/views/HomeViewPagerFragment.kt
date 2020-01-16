package com.demo.movies.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.demo.movies.R
import com.demo.movies.ui.adapter.MOVIES_PAGE_INDEX
import com.demo.movies.ui.adapter.MY_MOVIES_PAGE_INDEX
import com.demo.movies.ui.adapter.MoviesPagerAdapter
import com.demo.movies.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = MoviesPagerAdapter(this)

        // Set the text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MOVIES_PAGE_INDEX -> getString(R.string.movies_screen_title)
            MY_MOVIES_PAGE_INDEX -> getString(R.string.my_movies_screen_title)
            else -> null
        }
    }
}
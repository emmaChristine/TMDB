package com.demo.movies.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.movies.ui.views.MoviesFragment
import com.demo.movies.ui.views.MyMoviesFragment

const val MOVIES_PAGE_INDEX = 0
const val MY_MOVIES_PAGE_INDEX = 1

class MoviesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIES_PAGE_INDEX to { MoviesFragment() },
        MY_MOVIES_PAGE_INDEX to { MyMoviesFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
package com.example.beachapp.dashboardNavigationFragments

import BookmarkAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beachapp.R
import com.example.beachapp.bookmarkFragments.BookmarkedActivitiesFragment
import com.example.beachapp.bookmarkFragments.BookmarkedBeachesFragment
import com.example.beachapp.databinding.FragmentBookmarkBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BookmarkFragment : Fragment() {

    private lateinit var binding : FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)

        var adapter = BookmarkAdapter(requireActivity())
        adapter.addFragment(BookmarkedBeachesFragment(), "Beaches")
        adapter.addFragment(BookmarkedActivitiesFragment(), "Activities")

        binding.viewPager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(
                adapter.getPageTitle(position)
            )
        }.attach()

        return binding.root
    }
}
package com.nie.githublist.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nie.githublist.ui.main.fragment.PersonalInfoFragment
import com.nie.githublist.ui.main.fragment.UserListFragment

class GithubAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.add(UserListFragment())
        fragments.add(PersonalInfoFragment())
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
package com.nie.githublist.module

import androidx.fragment.app.FragmentActivity
import com.nie.githublist.ui.main.GithubAdapter
import com.nie.githublist.ui.main.UserListAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { UserListAdapter() }
    factory { (fragmentActivity: FragmentActivity) -> GithubAdapter(fragmentActivity) }
}
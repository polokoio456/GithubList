package com.nie.githublist.module

import com.nie.githublist.ui.main.MainViewModel
import com.nie.githublist.ui.userdetail.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { UserDetailViewModel(get()) }
}
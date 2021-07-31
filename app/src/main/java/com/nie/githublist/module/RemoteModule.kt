package com.nie.githublist.module

import com.nie.githublist.data.remote.NetworkService
import com.nie.githublist.data.remote.api.Api
import com.nie.githublist.repository.UserListRemoteRepository
import com.nie.githublist.repository.UserListRemoteRepositoryImpl
import org.koin.dsl.module

val remoteModule = module {
    single { NetworkService().create(Api::class.java) }
    factory<UserListRemoteRepository> { UserListRemoteRepositoryImpl(get()) }
}
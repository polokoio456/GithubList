package com.nie.githublist.module

import com.nie.githublist.repository.MainRepository
import com.nie.githublist.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}
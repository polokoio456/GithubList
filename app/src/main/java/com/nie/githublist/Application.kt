package com.nie.githublist

import android.app.Application
import com.nie.githublist.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    adapterModule,
                    repositoryModule,
                    viewModelModule,
                    remoteModule
                )
            )
        }
    }
}
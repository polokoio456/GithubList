package com.nie.githublist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.nie.githublist.data.paging.UserListRxPagingSource
import com.nie.githublist.data.remote.model.User
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi

class UserListRemoteRepositoryImpl(private val mainRepository: MainRepository) : UserListRemoteRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchUserList(): Flowable<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 30, prefetchDistance = 10),
            pagingSourceFactory = { UserListRxPagingSource(mainRepository) }
        ).flowable
    }
}
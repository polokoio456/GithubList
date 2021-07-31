package com.nie.githublist.repository

import androidx.paging.PagingData
import com.nie.githublist.data.remote.model.User
import io.reactivex.Flowable

interface UserListRemoteRepository {
    fun fetchUserList(): Flowable<PagingData<User>>
}
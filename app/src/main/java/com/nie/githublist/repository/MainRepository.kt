package com.nie.githublist.repository

import com.nie.githublist.data.remote.model.PersonalInfo
import com.nie.githublist.data.remote.model.User
import com.nie.githublist.data.remote.model.UserDetail
import io.reactivex.Single

interface MainRepository {
    fun fetchUserList(since: Int = 1): Single<List<User>>
    fun fetchUserDetail(name: String): Single<UserDetail>
    fun fetchPersonalInfo(): Single<PersonalInfo>
}
package com.nie.githublist.repository

import com.nie.githublist.data.remote.api.Api
import com.nie.githublist.data.remote.model.PersonalInfo
import com.nie.githublist.data.remote.model.User
import com.nie.githublist.data.remote.model.UserDetail
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl(private val api: Api) : MainRepository {

    override fun fetchUserList(since: Int): Single<List<User>> {
        return api.fetchUserList(since)
            .subscribeOn(Schedulers.io())
    }

    override fun fetchUserDetail(name: String): Single<UserDetail> {
        return api.fetchUserDetail(name)
            .subscribeOn(Schedulers.io())
    }

    override fun fetchPersonalInfo(): Single<PersonalInfo> {
        return api.fetchPersonalInfo()
            .subscribeOn(Schedulers.io())
    }
}
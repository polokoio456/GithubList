package com.nie.githublist.data.remote.api

import com.nie.githublist.data.Constants
import com.nie.githublist.data.remote.model.PersonalInfo
import com.nie.githublist.data.remote.model.User
import com.nie.githublist.data.remote.model.UserDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("users?per_page=30")
    fun fetchUserList(@Query("since") since: Int): Single<List<User>>

    @GET("users/{name}")
    fun fetchUserDetail(@Path("name") name: String): Single<UserDetail>

    @Headers("Authorization: token ${Constants.ACCESS_TOKEN}")
    @GET("user")
    fun fetchPersonalInfo(): Single<PersonalInfo>
}
package com.nie.githublist.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: String?,
    @SerializedName("html_url")
    val githubUrl: String
)
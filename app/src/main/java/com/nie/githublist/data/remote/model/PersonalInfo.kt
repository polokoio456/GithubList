package com.nie.githublist.data.remote.model

import com.google.gson.annotations.SerializedName

data class PersonalInfo(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("followers")
    val followers: Long,
    @SerializedName("following")
    val following: Long
)

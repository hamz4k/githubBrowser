package com.hklouch.data.network.contributor

import com.google.gson.annotations.SerializedName

data class UserJson(@SerializedName("id") val id: Int,
                    @SerializedName("login") val name: String,
                    @SerializedName("avatar_url") val avatarUrl: String
)
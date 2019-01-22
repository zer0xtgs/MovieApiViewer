package com.android.netflixroulette.network.response

import com.google.gson.annotations.SerializedName

data class Director(
    val id: Long,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String
)
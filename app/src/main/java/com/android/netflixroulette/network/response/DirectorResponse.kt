package com.android.netflixroulette.network.response

import com.google.gson.annotations.SerializedName

data class DirectorResponse(
    @SerializedName("results")
    val entries: List<Director>
)
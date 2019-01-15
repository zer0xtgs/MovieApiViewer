package com.android.netflixroulette.network.response

import com.google.gson.annotations.SerializedName

data class SearchByTitleResponse(
    @SerializedName("results")
    val entries: List<SearchByTitleEntry>
)
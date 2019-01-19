package com.android.netflixroulette.network.response

import com.android.netflixroulette.data.database.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val entries: List<Movie>
)
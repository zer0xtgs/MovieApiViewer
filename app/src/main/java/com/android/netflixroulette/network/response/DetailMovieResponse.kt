package com.android.netflixroulette.network.response

import com.android.netflixroulette.data.db.entity.Genre
import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    val genres: List<Genre>,
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double)
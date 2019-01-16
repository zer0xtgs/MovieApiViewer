package com.android.netflixroulette.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.netflixroulette.data.database.entity.Genre
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved_movies")
data class DetailMovieResponse(
    @PrimaryKey
    val id: Long,
    val genres: List<Genre>,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double) {
}
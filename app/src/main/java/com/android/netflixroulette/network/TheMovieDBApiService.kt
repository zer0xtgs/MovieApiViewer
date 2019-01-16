package com.android.netflixroulette.network

import com.android.netflixroulette.data.db.entity.DetailMovieResponse
import com.android.netflixroulette.network.response.SearchByTitleResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "e97735af5db66da1fac0437d58b1cd17"
const val BASE_URL = "https://api.themoviedb.org/3/"

//search movie link
//https://api.themoviedb.org/3/search/movie?api_key=e97735af5db66da1fac0437d58b1cd17&query=Aquaman

//movie info by id link
//https://api.themoviedb.org/3/movie/297802?api_key=e97735af5db66da1fac0437d58b1cd17

interface TheMovieDBApiService {

    // search by Title
    @GET("search/movie")
    fun getMovieByTitle(
        @Query("query") title : String,
        @Query("api_key") key : String = API_KEY
    ) : Deferred<SearchByTitleResponse>

    // movie info by ID
    @GET("movie/{id}")
    fun getMovieInfo(
        @Path("id") id : Long,
        @Query("api_key") key : String = API_KEY
    ) : Deferred<DetailMovieResponse>

    companion object {
        operator fun invoke() : TheMovieDBApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieDBApiService::class.java)
        }
    }
}
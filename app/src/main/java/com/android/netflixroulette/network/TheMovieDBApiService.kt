package com.android.netflixroulette.network

import com.android.netflixroulette.network.response.DirectorResponse
import com.android.netflixroulette.network.response.MovieResponse
import com.android.netflixroulette.network.response.MoviesByDirectorResponse
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

//search person link
//https://api.themoviedb.org/3/search/person?api_key=e97735af5db66da1fac0437d58b1cd17&query=Quentin+Tarantino

//search person combined_credits
//https://api.themoviedb.org/3/person/138/combined_credits?api_key=e97735af5db66da1fac0437d58b1cd17

interface TheMovieDBApiService {

    // search by Title
    @GET("search/movie")
    fun getMovieByTitle(
        @Query("query") title: String,
        @Query("api_key") key: String = API_KEY
    ): Deferred<MovieResponse>

    @GET("search/person")
    fun getDirector(
        @Query("query") title: String,
        @Query("api_key") key: String = API_KEY
    ): Deferred<DirectorResponse>

    @GET("person/{id}/combined_credits")
    fun getMovieByDirector(
        @Path("id") id: Long,
        @Query("api_key") key: String = API_KEY
    ): Deferred<MoviesByDirectorResponse>

    // TODO implement network connectivity interceptor
    companion object {
        operator fun invoke(): TheMovieDBApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieDBApiService::class.java)
        }
    }
}
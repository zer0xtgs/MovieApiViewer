package data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import data.network.response.SearchByTitleResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "e97735af5db66da1fac0437d58b1cd17"
const val BASE_URL = "https://api.themoviedb.org/3/"

//https://api.themoviedb.org/3/search/movie?api_key=e97735af5db66da1fac0437d58b1cd17&query=Aquaman

interface TheMovieDBApiService {

    // search by title
    @GET("search/movie")
    fun getMovieByTitle(
        @Query("query") title : String,
        @Query("api_key") key : String = API_KEY
    ) : Deferred<SearchByTitleResponse>

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
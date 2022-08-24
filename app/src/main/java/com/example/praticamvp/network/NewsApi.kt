package com.example.praticamvp.network

import com.example.praticamvp.const.API_KEY
import com.example.praticamvp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")

    suspend fun getBreakingNews(
        @Query("country") countryCode : String = "br",
        @Query("page") page : Int = 1,
        @Query("apiKey") key : String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") searchQuery : String,
        @Query("page") page : Int = 1,
        @Query("apiKey") key : String = API_KEY
    ) : Response<NewsResponse>
}
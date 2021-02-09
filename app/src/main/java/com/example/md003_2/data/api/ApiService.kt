package com.example.md003_2.data.api

import com.example.md003_2.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getMovie(@Query("t") title: String, @Query("apikey") apiKey:String): Movie
}
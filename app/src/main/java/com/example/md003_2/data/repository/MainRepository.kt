package com.example.md003_2.data.repository

import com.example.md003_2.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovie(movie: String) = apiHelper.getMovie(movie)
}
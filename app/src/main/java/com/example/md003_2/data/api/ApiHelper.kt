package com.example.md003_2.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getMovie(movie: String) = apiService.getMovie(movie, "28f8f9ed")
}
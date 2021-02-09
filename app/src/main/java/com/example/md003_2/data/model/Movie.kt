package com.example.md003_2.data.model


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Ratings")
    val ratings: List<Rating>,
    @SerializedName("Title")
    val title: String
)
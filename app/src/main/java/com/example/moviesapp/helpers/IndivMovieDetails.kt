package com.example.moviesapp.helpers

data class IndivMovieDetails(
    val id: String,
    val url: String,
    val primaryTitle: String,
    val originalTitle: String,
    val type: String,
    val description: String,
    val primaryImage: String,
    val averageRating: Double,
    val contentRating: String?,
    val startYear: Int,
    val releaseDate: String,
    val genres: List<String>,
    val runtimeMinutes: Int?,
    val directors: List<Person>,
    val writers: List<Person>,
    val cast: List<Person>
)

data class Person(
    val id: String,
    val url: String,
    val fullName: String
)


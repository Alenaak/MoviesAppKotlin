package com.example.moviesapp.helpers

data class UpcomingMovieResponse(
    val date: String,
    val titles: List<upcomingMovie>
)

data class upcomingMovie(
    val id: String,
    val url: String,
    val primaryTitle: String,
    val originalTitle: String,
    val type: String,
    val description: String,
    val primaryImage: String?,
    val contentRating: String?,
    val startYear: Int?,
    val endYear: Int?,
    val releaseDate: String?,
    val interests: List<String>?,
    val countriesOfOrigin: List<String>?,
    val externalLinks: List<String>?,
    val spokenLanguages: List<String>?,
    val filmingLocations: List<String>?,
    val productionCompanies: List<ProductionCom>?,
    val budget: Int?,
    val grossWorldwide: Int?,
    val genres: List<String>?,
    val isAdult: Boolean,
    val runtimeMinutes: Int?,
    val averageRating: Double?,
    val numVotes: Int?
)

data class ProductionCom(
    val id: String,
    val name: String
)


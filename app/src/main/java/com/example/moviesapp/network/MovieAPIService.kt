package com.example.moviesapp.network

import com.example.moviesapp.helpers.IndivMovieDetails
import com.example.moviesapp.helpers.Movie
import com.example.moviesapp.helpers.UpcomingMovieResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {

    @Headers(
        "x-rapidapi-host: imdb236.p.rapidapi.com",
        "x-rapidapi-key: YOUR KEY"
    )
    @GET("imdb/top250-movies")
    fun getTop250Movies(): Call<List<Movie>>

    @Headers(
        "x-rapidapi-host: imdb236.p.rapidapi.com",
        "x-rapidapi-key: YOUR KEY"
    )
    @GET("imdb/upcoming-releases")
    fun getUpcomingReleases(
        @Query("countryCode") countryCode: String,
        @Query("type") type: String
    ): Call<List<UpcomingMovieResponse>>

    @Headers(
        "X-RapidAPI-Host: imdb236.p.rapidapi.com",
        "X-RapidAPI-Key: YOUR KEY" 
    )
    @GET("imdb/{id}")
    fun getMovieDetails(@Path("id") movieId: String): Call<IndivMovieDetails>


}

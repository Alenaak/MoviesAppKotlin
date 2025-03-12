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
        "x-rapidapi-key: 6819195278mshdf5606b96257801p10c0f8jsnbfc9647f5dc9"
    )
    @GET("imdb/top250-movies")
    fun getTop250Movies(): Call<List<Movie>>

    @Headers(
        "x-rapidapi-host: imdb236.p.rapidapi.com",
        "x-rapidapi-key: 6819195278mshdf5606b96257801p10c0f8jsnbfc9647f5dc9"
    )
    @GET("imdb/upcoming-releases")
    fun getUpcomingReleases(
        @Query("countryCode") countryCode: String,
        @Query("type") type: String
    ): Call<List<UpcomingMovieResponse>>

    @Headers(
        "X-RapidAPI-Host: imdb236.p.rapidapi.com",
        "X-RapidAPI-Key: 6819195278mshdf5606b96257801p10c0f8jsnbfc9647f5dc9" // Replace with your actual API key
    )
    @GET("imdb/{id}")
    fun getMovieDetails(@Path("id") movieId: String): Call<IndivMovieDetails>


}

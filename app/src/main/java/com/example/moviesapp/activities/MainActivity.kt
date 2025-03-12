package com.example.moviesapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.UpcomingMovieAdapter1

import com.example.moviesapp.adapters.movieadapter

import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.helpers.IndivMovieDetails
import com.example.moviesapp.helpers.Movie
import com.example.moviesapp.helpers.UpcomingMovieResponse
import com.google.firebase.auth.FirebaseAuth

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: movieadapter
    private lateinit var upcomingMovieAdapter1: UpcomingMovieAdapter1
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        movieAdapter = movieadapter(emptyList())
        upcomingMovieAdapter1 = UpcomingMovieAdapter1(emptyList())

        // Set up RecyclerView with a default empty list
        binding.view1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.view1.adapter = movieAdapter
        binding.view2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.view2.adapter=upcomingMovieAdapter1
        fetchUpcomingMovies()
        fetchMovies()

        binding.editTextText3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                movieAdapter.filter(s.toString()) // Filters movies as user types
                upcomingMovieAdapter1.filter(s.toString())
            }
        })


    }

    private fun fetchMovies() {
        Log.d("MovieTitle", "Method called")

        RetrofitInstance.api.getTop250Movies().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    response.body()?.let { movies ->
                        Log.d("MovieTitle", "Fetched ${movies.size} movies")

                        // Pass the list to RecyclerView Adapter
                      movieAdapter.updateData(movies)
                    }
                } else {
                    Log.e("API_ERROR", "Response not successful: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.e("API_ERROR", "Failed to fetch movies: ${t.message}")
            }
        })
    }


    private fun fetchUpcomingMovies() {
        Log.d("MovieTitle", "Method called")

        RetrofitInstance.api.getUpcomingReleases("US", "MOVIE").enqueue(object : Callback<List<UpcomingMovieResponse>> {
            override fun onResponse(call: Call<List<UpcomingMovieResponse>>, response: Response<List<UpcomingMovieResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponses ->
                        Log.d("MovieTitle", "Fetched ${movieResponses.size} movie dates")

                        // ✅ Extract `titles` from each `UpcomingMovieResponse`
                        val movieList = movieResponses.flatMap { it.titles }

                        Log.d("MovieTitle", "Extracted ${movieList.size} movies")

                        // ✅ Pass extracted list to RecyclerView Adapter
                         upcomingMovieAdapter1.updateData(movieList)
                    }
                } else {
                    Log.e("API_ERROR", "Response not successful: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<UpcomingMovieResponse>>, t: Throwable) {
                Log.e("API_ERROR", "Failed to fetch movies: ${t.message}")
            }
        })
    }



}

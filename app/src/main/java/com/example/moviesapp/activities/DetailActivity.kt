package com.example.moviesapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.movieadapter
import com.example.moviesapp.databinding.ActivityDetailBinding
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.helpers.IndivMovieDetails
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private val binding by lazy {
       ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val movieId = intent.getStringExtra("MOVIE_ID")
        Log.d("DetailActivity", "Received Movie ID: $movieId")
        if (movieId != null) {
            fetchMovieDetails(movieId)
        }
    }

    fun fetchMovieDetails(movieId: String) {
        val apiService = RetrofitInstance.api
        apiService.getMovieDetails(movieId).enqueue(object : Callback<IndivMovieDetails> {
            override fun onResponse(call: Call<IndivMovieDetails>, response: Response<IndivMovieDetails>) {
                if (response.isSuccessful) {
                    response.body()?.let { movie ->
                        binding.actorslist.setText(movie.cast.joinToString { it.fullName })
                        Picasso.get().load(movie.primaryImage).into(binding.imageView8)
                        movie.averageRating.let { binding.movieTime.text = it.toString() }

                        binding.movieDate.setText(movie.releaseDate)
                        binding.movietitle.setText(movie.originalTitle)

                        binding.summarydetails.setText(movie.description)
                        Log.d("casturlstring", "onResponse: ${movie.cast.joinToString { it.url }} ")

                        Log.d("IndivMovieDetails", "Title: ${movie.primaryTitle}")
                        Log.d("IndivMovieDetails", "Director: ${movie.directors.joinToString { it.fullName }}")
                        Log.d("IndivMovieDetails", "Cast: ${movie.cast.joinToString { it.fullName }}")
                        Log.d("IndivMovieDetails", "Genres: ${movie.genres.joinToString()}")
                        Log.d("IndivMovieDetails", "Description: ${movie.description}")

                        // ✅ Update your UI with movie details
                    }
                } else {
                    Log.e("API_ERROR", "Response not successful: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<IndivMovieDetails>, t: Throwable) {
                Log.e("API_ERROR", "Failed to fetch movie details: ${t.message}")
            }
        })
    }

}
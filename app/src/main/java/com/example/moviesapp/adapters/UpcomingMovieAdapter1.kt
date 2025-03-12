package com.example.moviesapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.activities.DetailActivity
import com.example.moviesapp.databinding.ViewholderUpcomingFilmBinding

import com.example.moviesapp.helpers.upcomingMovie
import com.squareup.picasso.Picasso

class UpcomingMovieAdapter1(private var movies: List<upcomingMovie>) :
    RecyclerView.Adapter<UpcomingMovieAdapter1.MovieViewHolder>() {

    private var filteredMovies: List<upcomingMovie> = movies.toList()

    class MovieViewHolder(val binding: ViewholderUpcomingFilmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewholderUpcomingFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = filteredMovies[position]
        holder.binding.titletext.text = movie.primaryTitle
        Picasso.get().load(movie.primaryImage).resize(500, 750).centerCrop().into(holder.binding.pic)
        // Handle item click
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("MOVIE_ID", movie.id) // Passing Movie ID
            }
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int = filteredMovies.size

    fun updateData(newMovies: List<upcomingMovie>) {
        movies = newMovies
        filteredMovies = newMovies
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredMovies = if (query.isEmpty()) {
            movies
        } else {
            movies.filter { it.primaryTitle.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}


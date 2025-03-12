package com.example.moviesapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.activities.DetailActivity
import com.example.moviesapp.databinding.ViewholderFilmBinding
import com.example.moviesapp.helpers.Movie
import com.squareup.picasso.Picasso

class movieadapter(private var movies: List<Movie>



) :
    RecyclerView.Adapter<movieadapter.MovieViewHolder>() {

    private var filteredMovies: List<Movie> = movies.toList()

    class MovieViewHolder(private val binding: ViewholderFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.titletext.text = movie.originalTitle
            binding.scoretext.text = "${movie.averageRating}"
            Picasso.get().load(movie.primaryImage).into(binding.pic)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewholderFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = filteredMovies[position]
        holder.bind(movie)

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

    // Function to update the entire dataset
    fun updateData(newMovies: List<Movie>) {
        movies = newMovies
        filteredMovies = newMovies
        notifyDataSetChanged()
    }

    // Function to filter the movies based on search query
    fun filter(query: String) {
        filteredMovies = if (query.isEmpty()) {
            movies
        } else {
            movies.filter { it.originalTitle.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}

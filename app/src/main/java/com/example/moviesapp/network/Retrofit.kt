import com.example.moviesapp.network.MovieAPIService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://imdb236.p.rapidapi.com/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieAPIService by lazy {
        retrofit.create(MovieAPIService::class.java)
    }
}

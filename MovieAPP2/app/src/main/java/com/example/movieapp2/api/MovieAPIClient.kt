package com.example.movieapp2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIClient {

    companion object {
        private var movieApi: MovieAPI? = null

        fun getMovieApi(): MovieAPI? {

            if (movieApi == null) {
                movieApi = Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieAPI::class.java)

            }
            return movieApi
        }
    }

}
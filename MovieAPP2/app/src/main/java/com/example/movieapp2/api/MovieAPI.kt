package com.example.movieapp2.api

import com.example.movieapp2.models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("/")
    fun getMovieByID(@Query("i") id: String, @Query("apikey") api:String ="18bbe27a"): Call<Movies>

//    http://www.omdbapi.com/?apikey=18bbe27a&
}
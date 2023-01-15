package com.example.movieapp2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieapp2.api.MovieAPI
import com.example.movieapp2.api.MovieAPIClient
import com.example.movieapp2.database.AppDatabase
import com.example.movieapp2.database.relationship.MovieWithRatings
import com.example.movieapp2.models.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel(application: Application): AndroidViewModel(application)  {

    private var app: Application
    private val database: AppDatabase = AppDatabase.getInstance(application)


    private var movieAPI: MovieAPI
    private var MovieWithRatingsLiveData: MutableLiveData<MovieWithRatings> = MutableLiveData<MovieWithRatings>()




    init {
        movieAPI = MovieAPIClient.getMovieApi()!!
        app = application
        MovieWithRatingsLiveData = MutableLiveData<MovieWithRatings>()
    }


    fun loadDataFromDataBase(id: String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val moviesWithRatings = database.userDao().getMoviesWithRatings(id)


            withContext(Dispatchers.Main) {
                MovieWithRatingsLiveData.value = moviesWithRatings
            }

        }
    }

    fun getMovieWithRatingsLiveData():MutableLiveData<MovieWithRatings>{
        return MovieWithRatingsLiveData
    }

}
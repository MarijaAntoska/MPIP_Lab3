package com.example.movieapp2.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.DatabaseView
import com.example.movieapp2.api.MovieAPI
import com.example.movieapp2.api.MovieAPIClient
import com.example.movieapp2.database.AppDatabase
import com.example.movieapp2.models.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragmentViewModel(application: Application): AndroidViewModel(application) {

    private var movieAPI: MovieAPI
    private var app: Application
    private var moviesMutableLiveData: MutableLiveData<Movies>

    private val database: AppDatabase= AppDatabase.getInstance(application)



    init {
        movieAPI = MovieAPIClient.getMovieApi()!!
        app = application
        moviesMutableLiveData = MutableLiveData<Movies>()
    }

     fun searchMovieListById(id: String) {

        movieAPI.getMovieByID(id).enqueue(object : Callback<Movies> {

            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                if (response != null) {
                    val currentMovieList: Movies = response.body()
                    searchMoviesInDatabase(currentMovieList)
                    moviesMutableLiveData.value = currentMovieList

                    //  displayData(response.body())
                } else {
                    Toast.makeText(app, "Error", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Toast.makeText(app, "Error", Toast.LENGTH_LONG).show()

            }
        })

    }

    private fun searchMoviesInDatabase(currentMovieList: Movies) {

        CoroutineScope(Dispatchers.IO).launch {
            database.userDao().insertMoviesWithRatings(currentMovieList, currentMovieList.Ratings)
        }

    }

    fun getmoviesMutableLiveData(): MutableLiveData<Movies>{

        return moviesMutableLiveData
    }

}
package com.example.movieapp2.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Movies (
    @PrimaryKey
    val imdbID: String = "",
    val Title: String= "",
    val Plot: String= "",
    val Director: String= "",
    val Actors: String= "",
    val imdbRating: String= ""
){
    @Ignore
    val Ratings: MutableList<Rate> = mutableListOf()

 /*   constructor(
        imdbID: String,
        Title: String,
        Plot: String,
        Director: String,
        Actors: String,
        imdbRating: String,
        Ratings: MutableList<Rate>
    ) {
        this.imdbID = imdbID
        this.Title = Title
        this.Plot = Plot
        this.Director = Director
        this.Actors = Actors
        this.imdbRating = imdbRating
        this.Ratings = Ratings
    }*/
}
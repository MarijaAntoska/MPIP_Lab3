package com.example.movieapp2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Rate (

    @PrimaryKey
    val Source: String= "",
    val Value: String= "",

    var imdbID: String= ""
        /*get() = imdbID
        set(value){
        imdbID = value*/
    )


/*    constructor(Source: String, Value: String) {
        this.Source = Source
        this.Value = Value
    }*/

/*
}*/

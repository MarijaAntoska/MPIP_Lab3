package com.example.movieapp2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp2.R
import com.example.movieapp2.models.Rate

class MoviesAdapter(val context: Context, var allMovies: MutableList<Rate>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val moviesId: TextView
        val moviesTitle: TextView

        init {
            moviesId = view.findViewById(R.id.moviesId)
            moviesTitle = view.findViewById(R.id.moviesTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies, parent, false)

        return ViewHolder(view)
    }
    /* tt1869345
     tt1825683
     tt1825684
     tt1825696
     tt1825755*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = allMovies[position]
        holder.moviesId.text = currentMovie.Source
        holder.moviesTitle.text = currentMovie.Value

    }

    override fun getItemCount(): Int {
        return allMovies.size
    }

    fun upadateData(data: MutableList<Rate>) {
        this.allMovies = data
        this.notifyDataSetChanged()

/*
        this.notifyDataSetChanged()
*/
    }
}
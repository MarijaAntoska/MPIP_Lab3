package com.example.movieapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
/*
import androidx.lifecycle.Observer
*/
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp2.adapters.MoviesAdapter
import com.example.movieapp2.api.MovieAPI
import com.example.movieapp2.api.MovieAPIClient
import com.example.movieapp2.databinding.FragmentFirstBinding
import com.example.movieapp2.models.Movies
import com.example.movieapp2.models.Rate
import com.example.movieapp2.viewmodel.MovieListFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListFragment : Fragment() {

    private lateinit var movieAPI: MovieAPI
    private lateinit var movieAPIclient: MovieAPIClient
    private lateinit var movieTitle: TextView
    private lateinit var twMovieId: TextView
    private lateinit var director: TextView
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var firstFragmentViewModel: MovieListFragmentViewModel


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstFragmentViewModel = ViewModelProvider(this).get(MovieListFragmentViewModel::class.java)
        firstFragmentViewModel.getmoviesMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<Movies?> {
                override fun onChanged(t: Movies?) {
                    if (t != null) {
                        displayData(t)
                    } else {
                        Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()

                    }
                }
            })


        movieAPI = MovieAPIClient.getMovieApi()!!

        val movieId: EditText = view.findViewById<EditText>(R.id.movieID)
        movieTitle = view.findViewById(R.id.movieTitle)
        twMovieId = view.findViewById(R.id.twMovieId)
        director = view.findViewById(R.id.director)

        moviesRecyclerView = view.findViewById(R.id.allMoviesRecyclerView)
        moviesRecyclerView.layoutManager = LinearLayoutManager(activity)

/*
        val moviesRecyclerView = MoviesAdapter(requireActivity().applicationContext, mutableListOf<Movies>())
*/
        moviesAdapter = MoviesAdapter(requireActivity().applicationContext, mutableListOf<Rate>())
        moviesRecyclerView.adapter = moviesAdapter

        movieId.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                val listId: String = movieId.text.toString()
                firstFragmentViewModel.searchMovieListById(listId)

/*
                searchMovieListById(listId)
*/
                true

            } else {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                false
            }

        }

        val secondFragmentButton: Button = view.findViewById(R.id.secondFragmentAction)

        secondFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

/*     private fun searchMovieListById(id: String) {

         movieAPI.getMovieByID(id).enqueue(object : Callback<Movies> {

             override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                 if (response != null) {
                     displayData(response.body())
                 }
                 Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
             }

             override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                 Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()

             }
         })

     }*/

    private fun displayData(data: Movies) {

        movieTitle.text = data.Title
        twMovieId.text = data.imdbID
        director.text = data.Director

        moviesAdapter.upadateData(data.Ratings)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
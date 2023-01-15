package com.example.movieapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp2.adapters.MoviesAdapter
import com.example.movieapp2.database.relationship.MovieWithRatings
import com.example.movieapp2.databinding.FragmentSecondBinding
import com.example.movieapp2.models.Movies
import com.example.movieapp2.models.Rate
import com.example.movieapp2.viewmodel.MovieListFragmentViewModel
import com.example.movieapp2.viewmodel.SecondFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var secondFragmentViewModel : SecondFragmentViewModel
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesTitle: TextView
/*
    private lateinit var firstFragmentViewModel: MovieListFragmentViewModel
*/

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        secondFragmentViewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)

        secondFragmentViewModel.loadDataFromDataBase("tt1825683")
        secondFragmentViewModel.getMovieWithRatingsLiveData()
            .observe(viewLifecycleOwner, object : Observer<MovieWithRatings?>{
                override fun onChanged(data: MovieWithRatings?) {

                    if(data != null) {
                        displayDataFromDatabase(data)
                    }
                    else {
                        Toast.makeText(activity, "Error!!", Toast.LENGTH_LONG).show()
                    }
                }

            })


        moviesRecyclerView = view.findViewById(R.id.dataBaseList)
        moviesRecyclerView.layoutManager = LinearLayoutManager(activity)
        moviesAdapter = MoviesAdapter(requireActivity().applicationContext, mutableListOf<Rate>())
        moviesRecyclerView.adapter = moviesAdapter

        moviesTitle = view.findViewById(R.id.movieTitle)


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun displayDataFromDatabase(data: MovieWithRatings) {

        moviesTitle.text = data.Movies.Title
        moviesAdapter.upadateData(data.Movies.Ratings)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
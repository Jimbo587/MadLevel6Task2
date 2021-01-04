package com.example.madlevel6task2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.FragmentMovieListBinding
import com.example.madlevel6task2.model.MovieInfo
import com.example.madlevel6task2.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {

    private val movies = arrayListOf<MovieInfo>()
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by activityViewModels()
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(movies, ::initViews)
        setSubmitClick()
        observeMovie()
        binding.rvMovies.layoutManager = GridLayoutManager(activity, 2)
        binding.rvMovies.adapter = movieAdapter
    }

    private fun observeMovie(){
        viewModel.movie.observe(viewLifecycleOwner, {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews(movie: MovieInfo){
        Log.i("MOV", "going to set ${movie.title!!} as current movie")
        viewModel.setCurrentMovie(movie)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        Log.i("MOV", viewModel.getCurrentMovie().value!!.title!!)
    }

    private fun setSubmitClick(){
        binding.btnSubmit.setOnClickListener{
            val year  = binding.etYear.text!!

            if (year.isEmpty()) {
                Snackbar.make(btnSubmit, "The year is invalid",
                    Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            viewModel.getMovies(year.toString().toInt(), 1)

        }
    }
}
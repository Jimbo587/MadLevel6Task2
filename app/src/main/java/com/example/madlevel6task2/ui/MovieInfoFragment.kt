package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.madlevel6task2.databinding.FragmentMovieInfoBinding
import com.example.madlevel6task2.viewmodel.MovieViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieInfoFragment : Fragment() {

    private lateinit var binding: FragmentMovieInfoBinding
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        viewModel.getCurrentMovie().value?.let {
            binding.tvDate.text = it.releaseDate
            binding.tvSummary.text = it.overview
            binding.tvMovieTitle.text = it.title
            binding.tvRating.text = it.rating

            val baseImageUrl = "https://image.tmdb.org/t/p/w500/"
            Glide.with(requireContext()).load(baseImageUrl + it.posterPath).into(binding.ivMovie)
            Glide.with(requireContext()).load(baseImageUrl + it.backDropPath).into(binding.ivMovieBg)
        }
    }
}
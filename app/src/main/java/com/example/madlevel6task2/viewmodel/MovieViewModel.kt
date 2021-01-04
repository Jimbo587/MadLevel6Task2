package com.example.madlevel6task2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.model.MovieInfo
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {

    private val movieRepository = MovieRepository()
    val movie = movieRepository.movie
    var currentMovie: MutableLiveData<MovieInfo> = MutableLiveData()
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    fun getCurrentMovie(): LiveData<MovieInfo>{
        return currentMovie
    }

    fun setCurrentMovie(movie: MovieInfo){
        currentMovie.value = movie
    }

    fun getMovies(year: Int, page : Int) {
        viewModelScope.launch {
            try {
                //the movieRepository sets it's own livedata property
                //our own movie LiveData property points to te one in that repository
                movieRepository.getMovies(year, page)
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Movie error", error.cause.toString())
            }
        }
    }
}
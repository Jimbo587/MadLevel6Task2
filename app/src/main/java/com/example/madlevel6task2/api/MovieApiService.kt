package com.example.madlevel6task2.api

import com.example.madlevel6task2.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("year") year: Int, @Query("page") page: Int): Movie

}
package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

class MovieInfo
    (var title: String? = null,
     @SerializedName("backdrop_path")
     var backDropPath: String? = null,
     @SerializedName("poster_path")
     var posterPath: String? = null,
     @SerializedName("release_date")
     var releaseDate: String? = null,
     @SerializedName("vote_average")
     var rating: String? = null,
     var overview: String? = null)
{
}
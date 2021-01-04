package com.example.madlevel6task2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class Movie(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null,
    var results: List<MovieInfo>? = null
){
}
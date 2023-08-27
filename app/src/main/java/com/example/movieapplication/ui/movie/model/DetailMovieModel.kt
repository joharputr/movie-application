package com.example.movieapplication.ui.movie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovieModel(
    @SerializedName("backdrop_path") var backdrop_path: String,
    @SerializedName("original_title") var original_title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("release_date") var release_date: String,
) : Parcelable
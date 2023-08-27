package com.example.movieapplication.ui.movie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class DiscoverMovieModel<T>(
    @SerializedName("page") var page: Int,
    @SerializedName("total_pages") var total_pages: Int,
    @SerializedName("total_results") var total_results: Int,
    @SerializedName("results") var results: ArrayList<T> = arrayListOf()
)

@Parcelize
data class DiscoverMovieResultModel(
    @SerializedName("title") var title: String,
    @SerializedName("id") var id: Int,
    @SerializedName("backdrop_path") var backdrop_path: String,
) : Parcelable

@Parcelize
data class UserReviewResultModel(
    @SerializedName("id") var id: String,
    @SerializedName("author") var author: String,
    @SerializedName("content") var content: String,
    @SerializedName("updated_at") var updated_at: String,
) : Parcelable@Parcelize

data class DataYoutubeModel(
    @SerializedName("key") var key: String,
) : Parcelable
package com.example.movieapplication.ui.genre.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreModel(
    @SerializedName("genres") var genres: ArrayList<Genres> = arrayListOf()
) : Parcelable

@Parcelize
data class Genres(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
) : Parcelable
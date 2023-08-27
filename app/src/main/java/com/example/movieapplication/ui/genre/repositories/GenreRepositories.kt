package com.example.movieapplication.ui.genre.repositories

import com.example.movieapplication.core.API
import javax.inject.Inject

class GenreRepositories @Inject constructor(private val api: API) {
    suspend fun getListGenre() = api.getListGenre()


}
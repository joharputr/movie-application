package com.example.movieapplication.ui.genre.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.base.handleResponseV2
import com.example.movieapplication.ui.genre.model.Genres
import com.example.movieapplication.ui.genre.repositories.GenreRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val genreRepositories: GenreRepositories) :
    BaseViewModel() {

    val genreList = MutableLiveData<ArrayList<Genres>>()

    fun getGenreList() {
        viewModelScope.launch {
            changeShow(true)
            genreRepositories.getListGenre().handleResponseV2(
                onSuccess = {
                    genreList.postValue(it?.genres)
                    changeShow(false)
                }, onFailed = {
                    changeShow(false)
                }
            )

        }
    }
}
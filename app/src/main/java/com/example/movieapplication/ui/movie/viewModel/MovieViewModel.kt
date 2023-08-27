package com.example.movieapplication.ui.movie.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.base.handleResponseV2
import com.example.movieapplication.ui.movie.model.DataYoutubeModel
import com.example.movieapplication.ui.movie.model.DetailMovieModel
import com.example.movieapplication.ui.movie.model.DiscoverMovieModel
import com.example.movieapplication.ui.movie.model.DiscoverMovieResultModel
import com.example.movieapplication.ui.movie.model.UserReviewResultModel
import com.example.movieapplication.ui.movie.repositories.MovieRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepositories: MovieRepositories
) : BaseViewModel() {

    val detailMovieModel = MutableLiveData<DetailMovieModel>()
    val dataYoutubeModel = MutableLiveData<DiscoverMovieModel<DataYoutubeModel>>()

    fun getDataMovie(with_genres: Int): Flow<PagingData<DiscoverMovieResultModel>> {
        return movieRepositories.getDataMovie(with_genres).cachedIn(viewModelScope)
    }
    fun getUserReview(id: Int): Flow<PagingData<UserReviewResultModel>> {
        return movieRepositories.getUserReview(id).cachedIn(viewModelScope)
    }

    fun getDetailMovie(id: Int) {
        viewModelScope.launch {
            changeShow(true)
            movieRepositories.getDetailMovie(id).handleResponseV2(
                onSuccess = {
                    detailMovieModel.postValue(it)
                    changeShow(false)
                }, onFailed = {
                    changeShow(false)
                }
            )
        }

    }
    fun getDataYoutube(id: Int) {
        viewModelScope.launch {
            changeShow(true)
            movieRepositories.getDataYoutube(id).handleResponseV2(
                onSuccess = {
                    dataYoutubeModel.postValue(it)
                    changeShow(false)
                }, onFailed = {
                    changeShow(false)
                }
            )
        }

    }
}
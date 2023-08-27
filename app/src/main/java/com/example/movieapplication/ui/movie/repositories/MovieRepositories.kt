package com.example.movieapplication.ui.movie.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapplication.core.API
import com.example.movieapplication.ui.movie.model.DiscoverMovieResultModel
import com.example.movieapplication.ui.movie.model.UserReviewResultModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositories @Inject constructor(private val api: API) {

    fun getDataMovie(with_genres: Int): Flow<PagingData<DiscoverMovieResultModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(api = api, with_genres = with_genres)
            }
        ).flow
    }
    fun getUserReview(id: Int): Flow<PagingData<UserReviewResultModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserReviewPagingSource(api = api, id = id)
            }
        ).flow
    }

    suspend fun getDetailMovie(id :Int)  = api.getDetailMovie(id)
    suspend fun getDataYoutube(id :Int)  = api.getDataYoutube(id)
}
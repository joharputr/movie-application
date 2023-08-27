package com.example.movieapplication.ui.movie.repositories

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapplication.core.API
import com.example.movieapplication.ui.movie.model.UserReviewResultModel
import retrofit2.HttpException


class UserReviewPagingSource(private val api: API, private val id: Int) :
    PagingSource<Int, UserReviewResultModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserReviewResultModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserReviewResultModel> {
        Log.d("paramKey", " = ${params.key}")
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {

            val response = api.getUserReview(
                id = id,
                page = position
            )

            val data = response.body()?.results ?: emptyList()
            val nextKey =
                if (data.isEmpty()) {
                    null
                } else {
                    position + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = data,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            Log.d("excpp", " ${exception.localizedMessage}")
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.d("excpp", " ${exception.localizedMessage}")
            return LoadResult.Error(exception)
        }
    }
}
package com.example.movieapplication.ui.movie.repositories

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapplication.core.API
import com.example.movieapplication.ui.movie.model.DiscoverMovieResultModel
import retrofit2.HttpException

const val NETWORK_PAGE_SIZE = 10
 const val STARTING_PAGE_INDEX = 1

class MoviePagingSource (private val api: API, private val with_genres: Int) :
    PagingSource<Int, DiscoverMovieResultModel>() {
    override fun getRefreshKey(state: PagingState<Int, DiscoverMovieResultModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovieResultModel> {
        Log.d("paramKey", " = ${params.key}")
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {

            val response = api.getMovie(
                with_genres =with_genres,
                page = position
            )

            val movies = response.body()?.results ?: emptyList()
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    position + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = movies,
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
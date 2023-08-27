package com.example.movieapplication.core

import com.example.movieapplication.ui.genre.model.GenreModel
import com.example.movieapplication.ui.genre.model.Genres
import com.example.movieapplication.ui.movie.model.DataYoutubeModel
import com.example.movieapplication.ui.movie.model.DetailMovieModel
import com.example.movieapplication.ui.movie.model.DiscoverMovieModel
import com.example.movieapplication.ui.movie.model.DiscoverMovieResultModel
import com.example.movieapplication.ui.movie.model.UserReviewResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("genre/movie/list?language=en&api_key=9b3efc88fed3cb0e25a0849788f05166")
    suspend fun getListGenre(
    ): Response<GenreModel>

    //
    @GET("discover/movie?api_key=9b3efc88fed3cb0e25a0849788f05166")
    suspend fun getMovie(
        @Query("with_genres") with_genres: Int,
        @Query("page") page: Int
    ): Response<DiscoverMovieModel<DiscoverMovieResultModel>>
    //
    @GET("movie/{id}?language=en-US&api_key=9b3efc88fed3cb0e25a0849788f05166")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
    ): Response<DetailMovieModel>

    @GET("movie/{id}/videos?api_key=9b3efc88fed3cb0e25a0849788f05166")
    suspend fun getDataYoutube(
        @Path("id") id: Int,
    ): Response<DiscoverMovieModel<DataYoutubeModel>>

    @GET("movie/{id}/reviews?api_key=9b3efc88fed3cb0e25a0849788f05166")
    suspend fun getUserReview(
        @Path("id") id: Int,
        @Query("page") page: Int
    ): Response<DiscoverMovieModel<UserReviewResultModel>>

}
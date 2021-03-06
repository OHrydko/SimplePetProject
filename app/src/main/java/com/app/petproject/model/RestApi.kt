package com.app.petproject.model

import com.app.petproject.entiti.Movie
import com.app.petproject.entiti.info.Overview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    /**
     * get list trend movies
     */
    @GET("3/trending/{media_type}/{time_window}")
    suspend fun getMovie(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<Movie>

    /**
     * get data about film by id
     */
    @GET("3/movie/{movie_id}")
    suspend fun getOverview(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Response<Overview>
}
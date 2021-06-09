package com.app.petproject.repository

import com.app.petproject.BuildConfig
import com.app.petproject.entiti.Movie
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.information.Overview
import com.app.petproject.model.RestApi


class Repository(private val restApi: RestApi) : BaseDataSource() {


    suspend fun getMovie(): Resource<Movie> = getResult {
        restApi.getMovie(
            api_key = BuildConfig.KEY,
            media_type = "movie",
            time_window = "day",
            page = 1
        )
    }

    suspend fun getOverview(id: Int): Resource<Overview> = getResult {
        restApi.getOverview(movie_id = id, api_key = BuildConfig.KEY, language = "en")
    }
}
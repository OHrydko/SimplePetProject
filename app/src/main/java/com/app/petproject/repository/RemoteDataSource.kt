package com.app.petproject.repository

import com.app.petproject.BaseDataSource
import com.app.petproject.model.RestApi

class RemoteDataSource(
    private val restApi: RestApi
) : BaseDataSource() {
    private val key: String = "8e0652e0b30813d0c7410a3498a2e053"

    suspend fun getCharacters() = getResult {
        restApi.getMovie(
            api_key = key,
            media_type = "movie",
            time_window = "day",
            page = 1
        )
    }

    suspend fun getOverview(id: Int) = getResult {
        restApi.getOverview(movie_id = id, api_key = key, language = "en")
    }

}
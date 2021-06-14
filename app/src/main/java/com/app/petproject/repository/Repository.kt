package com.app.petproject.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.petproject.BuildConfig
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.Results
import com.app.petproject.entiti.info.Overview
import com.app.petproject.model.RestApi
import com.app.petproject.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow


class Repository(private val restApi: RestApi) : BaseDataSource() {


    suspend fun getOverview(id: Int): Resource<Overview> = getResult {
        restApi.getOverview(movie_id = id, api_key = BuildConfig.KEY, language = "en")
    }

    /**
     * get list movies from MoviePagingSource via restApi
     */
    fun getMovies(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Results>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(restApi) }
        ).flow
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, enablePlaceholders = true)
    }
}
package com.app.petproject.paging

import androidx.paging.PagingSource
import com.app.petproject.BuildConfig
import com.app.petproject.entiti.Results
import com.app.petproject.model.RestApi
import retrofit2.HttpException

class MoviePagingSource(
    private val restApi: RestApi,
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            //network call
            val response = restApi.getMovie(
                api_key = BuildConfig.KEY,
                media_type = "movie",
                time_window = "day",
                page = nextPage
            )
            //check responce
            return if (response.isSuccessful) {
                val data = response.body()
                //update data
                LoadResult.Page(
                    data = data?.results.orEmpty(),
                    prevKey = if (nextPage == 1) null else nextPage.minus(1),
                    nextKey = data?.page?.plus(1)
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
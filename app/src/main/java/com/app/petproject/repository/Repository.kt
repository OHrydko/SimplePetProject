package com.app.petproject.repository

import androidx.lifecycle.LiveData
import com.app.petproject.entiti.Movie
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.information.Overview

interface IMainRepository {
    fun getMovie(): LiveData<Resource<Movie?>>
    fun getOverview(id: Int): LiveData<Resource<Overview?>>
}

class Repository(private val remoteDataSource: RemoteDataSource) :
    IMainRepository {
    override fun getMovie() =
        performGetOperation(
            networkCall = { remoteDataSource.getCharacters() }
        )

    override fun getOverview(id: Int) =
        performGetOperation(networkCall = {
            remoteDataSource.getOverview(id)
        })
}
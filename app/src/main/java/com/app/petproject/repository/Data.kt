package com.app.petproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.app.petproject.entiti.Resource
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(
    networkCall: suspend () -> Resource<T>
): LiveData<Resource<T?>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = networkCall.invoke().data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }
package com.app.petproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.petproject.entiti.Results
import com.app.petproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var movie: Flow<PagingData<Results>>? = null

    fun getMovies() {
        movie = repository.getMovies()
            .cachedIn(viewModelScope)
    }


}
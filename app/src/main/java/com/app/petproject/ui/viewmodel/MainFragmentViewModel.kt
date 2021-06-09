package com.app.petproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.petproject.entiti.Movie
import com.app.petproject.entiti.Resource
import com.app.petproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _response = MutableLiveData<Resource<Movie>>()
    val response: LiveData<Resource<Movie>> = _response

    fun getMovie() {
        _response.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(repository.getMovie())
        }
    }


}
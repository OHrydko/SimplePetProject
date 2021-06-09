package com.app.petproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.information.Overview
import com.app.petproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _response = MutableLiveData<Resource<Overview>>()
    val response: LiveData<Resource<Overview>> = _response

    fun getOverview(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(repository.getOverview(id))
        }
    }
}
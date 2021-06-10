package com.app.petproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.info.Overview
import com.app.petproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _response = MutableStateFlow<Resource<Overview>>(Resource.loading(null))
    val response: StateFlow<Resource<Overview>> = _response.asStateFlow()

    fun getOverview(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.value = repository.getOverview(id)
        }
    }
}
package com.app.petproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.petproject.BaseViewModel
import com.app.petproject.IViewModel
import com.app.petproject.entiti.Resource
import com.app.petproject.entiti.information.Overview
import com.app.petproject.repository.IMainRepository

interface IOverviewFragmentViewModel : IViewModel {
    fun getOverview(id: Int)
    var movie: LiveData<Resource<Overview?>>
}

class OverviewViewModel(private val repository: IMainRepository) : BaseViewModel(),
    IOverviewFragmentViewModel {

    override var movie: LiveData<Resource<Overview?>> = MutableLiveData()

    override fun getOverview(id: Int) {
        movie = repository.getOverview(id)
    }




}
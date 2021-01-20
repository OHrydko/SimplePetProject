package com.app.petproject.ui.viewmodel

import androidx.lifecycle.LiveData
import com.app.petproject.BaseViewModel
import com.app.petproject.IViewModel
import com.app.petproject.entiti.Movie
import com.app.petproject.entiti.Resource
import com.app.petproject.repository.IMainRepository

interface IMainFragmentViewModel : IViewModel {
    val movie: LiveData<Resource<Movie?>>

}

class MainFragmentViewModel(repository: IMainRepository) : BaseViewModel(),
    IMainFragmentViewModel {

    override val movie: LiveData<Resource<Movie?>> = repository.getMovie()


}
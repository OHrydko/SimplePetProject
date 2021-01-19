package com.app.petproject.dagger


import androidx.lifecycle.ViewModelProviders
import com.app.petproject.repository.IMainRepository
import com.app.petproject.ui.view.MainFragment
import com.app.petproject.ui.viewmodel.IMainFragmentViewModel
import com.app.petproject.ui.viewmodel.MainFragmentViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationBinder {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun mainFragment(): MainFragment

}

@Module
class FragmentViewModelModule {

    @Provides
    fun mainVM(
        fragment: MainFragment,
        repository: IMainRepository
    ): IMainFragmentViewModel =
        ViewModelProviders.of(
            fragment, AppViewModelFactory.forInstance {
                MainFragmentViewModel(repository)
            }
        ).get(MainFragmentViewModel::class.java)
//
//    @Provides
//    fun iRegisterVM(
//        activity: RegisterFragment,
//        dao: Dao
//    ): IRegistrationViewModel =
//        ViewModelProviders.of(
//            activity, AppViewModelFactory.forInstance {
//                RegistrationViewModel(dao)
//            }
//        ).get(RegistrationViewModel::class.java)

}
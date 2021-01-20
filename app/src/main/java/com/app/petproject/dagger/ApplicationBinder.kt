package com.app.petproject.dagger


import androidx.lifecycle.ViewModelProvider
import com.app.petproject.repository.IMainRepository
import com.app.petproject.ui.view.MainFragment
import com.app.petproject.ui.view.OverviewFragment
import com.app.petproject.ui.viewmodel.IMainFragmentViewModel
import com.app.petproject.ui.viewmodel.IOverviewFragmentViewModel
import com.app.petproject.ui.viewmodel.MainFragmentViewModel
import com.app.petproject.ui.viewmodel.OverviewViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationBinder {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun overviewFragment(): OverviewFragment

}

@Module
class FragmentViewModelModule {

    @Provides
    fun mainVM(
        fragment: MainFragment,
        repository: IMainRepository
    ): IMainFragmentViewModel =
        ViewModelProvider(
            fragment, AppViewModelFactory.forInstance {
                MainFragmentViewModel(repository)
            }
        ).get(MainFragmentViewModel::class.java)

    @Provides
    fun overviewVM(
        fragment: OverviewFragment,
        repository: IMainRepository
    ): IOverviewFragmentViewModel =
        ViewModelProvider(
            fragment, AppViewModelFactory.forInstance {
                OverviewViewModel(repository)
            }
        ).get(OverviewViewModel::class.java)
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
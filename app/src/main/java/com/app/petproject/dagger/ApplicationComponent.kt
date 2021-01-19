package com.app.petproject.dagger

import android.app.Application
import com.app.petproject.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationBinder::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MyApp)
}
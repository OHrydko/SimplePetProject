package com.app.petproject

import android.app.Application
import com.app.petproject.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {

        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)

        super.onCreate()

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
package com.inter.courseapp

import android.app.Application
import com.inter.courseapp.di.component.AppComponent
import com.inter.courseapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import timber.log.Timber


class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var hasAndroidInjector: DispatchingAndroidInjector<Any>

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .setApplication(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = hasAndroidInjector
}
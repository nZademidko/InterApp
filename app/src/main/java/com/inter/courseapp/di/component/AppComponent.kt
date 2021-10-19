package com.inter.courseapp.di.component

import android.app.Application
import com.inter.courseapp.MainApplication
import com.inter.courseapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setApplication(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)
}
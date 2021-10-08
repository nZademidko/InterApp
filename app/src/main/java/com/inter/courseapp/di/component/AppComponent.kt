package com.inter.courseapp.di.component

import android.app.Application
import com.inter.courseapp.App
import com.inter.courseapp.di.modules.ActivityModule
import com.inter.courseapp.di.modules.AppModule
import com.inter.courseapp.di.modules.FragmentModule
import com.inter.courseapp.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setApplication(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}
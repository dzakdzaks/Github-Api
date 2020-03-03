package com.dzakdzaks.github_api.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:56.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        BaseModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    override fun inject(instance: DaggerApplication)

}
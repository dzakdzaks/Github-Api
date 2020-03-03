package com.dzakdzaks.github_api.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakdzaks.github_api.di.annotations.ViewModelKey
import com.dzakdzaks.github_api.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:45.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
package com.dzakdzaks.github_api.di

import com.dzakdzaks.github_api.base.ViewModelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:54.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Module
abstract class BaseModule {
    @ContributesAndroidInjector
    internal abstract fun contributeViewModelActivity(): ViewModelActivity
}
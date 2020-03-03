package com.dzakdzaks.github_api.di

import com.dzakdzaks.github_api.di.annotations.ActivityScope
import com.dzakdzaks.github_api.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:58.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}
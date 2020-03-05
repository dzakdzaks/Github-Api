package com.dzakdzaks.github_api

import android.content.Context
import com.dzakdzaks.github_api.di.DaggerAppComponent
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 17:39.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api
 * ==================================//==================================
 * ==================================//==================================
 */

class MyApp : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun appContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector() = appComponent

}
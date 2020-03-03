package com.dzakdzaks.github_api.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 16:03.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.base
 * ==================================//==================================
 * ==================================//==================================
 */

abstract class ViewModelActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected inline fun <reified VM : ViewModel>
            injectViewModels(): Lazy<VM> = viewModels { viewModelFactory }

}
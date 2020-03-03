package com.dzakdzaks.github_api.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:49.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Suppress("UNCHECKED_CAST")
@Singleton
class AppViewModelFactory @Inject constructor(
    private val viewModel: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModel[modelClass]
            ?: viewModel.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        requireNotNull(creator) { "unknown model class $modelClass" }
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


}
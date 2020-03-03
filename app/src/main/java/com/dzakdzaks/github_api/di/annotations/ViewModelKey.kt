package com.dzakdzaks.github_api.di.annotations

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:46.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di.annotations
 * ==================================//==================================
 * ==================================//==================================
 */

@MapKey
@Target(AnnotationTarget.FUNCTION)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
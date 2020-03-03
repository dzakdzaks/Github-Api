package com.dzakdzaks.github_api.repository

import androidx.lifecycle.MutableLiveData

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 16:08.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.repository
 * ==================================//==================================
 * ==================================//==================================
 */
interface Repository {
    var isLoading: MutableLiveData<Boolean>
}
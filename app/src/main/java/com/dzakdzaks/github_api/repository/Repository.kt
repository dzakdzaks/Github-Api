package com.dzakdzaks.github_api.repository

import androidx.lifecycle.MutableLiveData

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 06 March 2020 at 14:35.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.repository
 * ==================================//==================================
 * ==================================//==================================
 */
interface Repository {
    var isLoading: MutableLiveData<Boolean>
}
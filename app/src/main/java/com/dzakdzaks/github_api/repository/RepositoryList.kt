package com.dzakdzaks.github_api.repository

import androidx.lifecycle.MutableLiveData
import com.utsman.recycling.extentions.NetworkState

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 16:08.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.repository
 * ==================================//==================================
 * ==================================//==================================
 */
interface RepositoryList {
    var networkState: MutableLiveData<NetworkState>
}
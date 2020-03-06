package com.dzakdzaks.github_api.repository

import androidx.lifecycle.MutableLiveData
import com.dzakdzaks.github_api.entity.entities.UserDetail
import com.dzakdzaks.github_api.network.ApiResponse
import com.dzakdzaks.github_api.network.client.UserDetailClient
import com.dzakdzaks.github_api.network.message
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 06 March 2020 at 14:07.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.repository
 * ==================================//==================================
 * ==================================//==================================
 */
@Singleton
class UserDetailRepository @Inject constructor(
    private val userDetailClient: UserDetailClient
) : Repository {

    override var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        Timber.d("Inject userDetailRepo")
    }

    fun fetchUserDetail(
        username: String,
        error: (String) -> Unit
    ): MutableLiveData<UserDetail> {
        val userDetail = MutableLiveData<UserDetail>()
        isLoading.postValue(true)
        userDetailClient.fetchUserDetail(username) { response ->
            isLoading.postValue(false)
            Timber.d("polpol: $response")
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                        userDetail.postValue(it)
                    }
                }
                is ApiResponse.Failure.Error -> {
                    error(response.message())
                }
                is ApiResponse.Failure.Exception -> {
                    error(response.message())
                }
            }
        }
        return userDetail
    }

}
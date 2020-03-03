package com.dzakdzaks.github_api.repository

import androidx.lifecycle.MutableLiveData
import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.network.ApiResponse
import com.dzakdzaks.github_api.network.client.UserClient
import com.dzakdzaks.github_api.network.message
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 16:07.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.repository
 * ==================================//==================================
 * ==================================//==================================
 */

@Singleton
class UserRepository @Inject constructor(
    private val userClient: UserClient
) : Repository {

    override var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        Timber.d("Inject UserRepository")
    }

    fun searchUsers(query: String, error: (String) -> Unit): MutableLiveData<List<Users>> {
        val listUsers = MutableLiveData<List<Users>>()
        this.isLoading.postValue(true)
        userClient.fetchUsersResult(query) { response ->
            this.isLoading.postValue(false)
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let { data ->
                        listUsers.postValue(data.items)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }
        return listUsers
    }
}
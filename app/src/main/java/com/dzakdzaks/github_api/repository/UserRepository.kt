package com.dzakdzaks.github_api.repository

import androidx.lifecycle.MutableLiveData
import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.network.ApiResponse
import com.dzakdzaks.github_api.network.client.UserClient
import com.dzakdzaks.github_api.network.message
import com.utsman.recycling.extentions.NetworkState
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
) : RepositoryList {

    override var networkState: MutableLiveData<NetworkState> = MutableLiveData()

    init {
        Timber.d("Inject UserRepository")
    }

    var url: String = ""

    fun searchUsers(
        query: String,
        page: Int,
        perPage: Int,
        error: (String) -> Unit
    ): MutableLiveData<List<Users>> {
        val listUsersSearch = MutableLiveData<List<Users>>()
        this.networkState.postValue(NetworkState.LOADING)
        userClient.fetchUsersResultSearch(query, page, perPage) { response ->
            this.networkState.postValue(NetworkState.LOADED)
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let { data ->
                        listUsersSearch.postValue(data.items)
                    }
                }
                is ApiResponse.Failure.Error -> {
                    error(response.message())
                    networkState.postValue(NetworkState.error(response.message()))
                }
                is ApiResponse.Failure.Exception -> {
                    error(response.message())
                    networkState.postValue(NetworkState.error(response.message()))
                }
            }
        }
        return listUsersSearch
    }

    fun fetchUsers(error: (String) -> Unit): MutableLiveData<List<Users>> {
        val listUsers = MutableLiveData<List<Users>>()
        if (url == "") {
            this.networkState.postValue(NetworkState.LOADING)
            userClient.fetchUserResult { response ->
                this.networkState.postValue(NetworkState.LOADED)
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let {
                            listUsers.postValue(it)
                            url = response.url.toString()
                            Timber.d("wakwaw: $url")
                        }
                    }
                    is ApiResponse.Failure.Error -> {
                        error(response.message())
                        networkState.postValue(NetworkState.error(response.message()))
                    }
                    is ApiResponse.Failure.Exception -> {
                        error(response.message())
                        networkState.postValue(NetworkState.error(response.message()))
                    }
                }
            }
        } else {
            this.networkState.postValue(NetworkState.LOADING)
            userClient.fetchUserResultPaginate(url) { response ->
                this.networkState.postValue(NetworkState.LOADED)
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let {
                            listUsers.postValue(it)
                            url = response.url.toString()
                            Timber.d("wakwaw: $url")
                        }
                    }
                    is ApiResponse.Failure.Error -> {
                        error(response.message())
                        networkState.postValue(NetworkState.error(response.message()))
                    }
                    is ApiResponse.Failure.Exception -> {
                        error(response.message())
                        networkState.postValue(NetworkState.error(response.message()))
                    }
                }
            }
        }
        return listUsers
    }
}
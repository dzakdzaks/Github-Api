package com.dzakdzaks.github_api.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.repository.UserRepository
import com.utsman.recycling.extentions.NetworkState
import timber.log.Timber
import javax.inject.Inject

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 16:23.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.ui
 * ==================================//==================================
 * ==================================//==================================
 */

class MainActivityViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val messageSearch: MutableLiveData<String> = MutableLiveData()
    val messageFetch: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection MainActivityViewModel")
    }

    fun networkState(): LiveData<NetworkState> = userRepository.networkState

    fun searchUsers(query: String, page: Int, perPage: Int): LiveData<List<Users>> {
        userRepository.url = ""
        var list = MutableLiveData<List<Users>>()
        if (query != "") {
            list = userRepository.searchUsers(query, page, perPage) { msg ->
                messageSearch.postValue(msg)
            }
        }
        return list
    }

    fun fetchUsers(): LiveData<List<Users>> {
        return userRepository.fetchUsers { messageFetch.postValue(it) }
    }

}
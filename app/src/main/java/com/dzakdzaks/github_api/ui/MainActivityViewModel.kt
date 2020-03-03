package com.dzakdzaks.github_api.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.repository.UserRepository
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

//    private var querySearch: MutableLiveData<String> = MutableLiveData()
//    var userList: LiveData<List<Users>>

    val message: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection MainActivityViewModel")

//        this.userList = querySearch.switchMap { querySearch ->
//            userRepository.searchUsers(querySearch) {message.postValue(it)}
//        }
    }

    fun searchUsers(query: String): LiveData<List<Users>> {
        var list = MutableLiveData<List<Users>>()
        if (query != "") {
            list = userRepository.searchUsers(query) { message.postValue(it) }
        }
        return list
    }

    fun isLoading(): LiveData<Boolean> = userRepository.isLoading

}
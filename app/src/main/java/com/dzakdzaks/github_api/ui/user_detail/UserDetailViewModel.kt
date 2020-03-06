package com.dzakdzaks.github_api.ui.user_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakdzaks.github_api.entity.entities.UserDetail
import com.dzakdzaks.github_api.repository.UserDetailRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Wednesday, 04 March 2020 at 10:59.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.ui.user_detail
 * ==================================//==================================
 * ==================================//==================================
 */

class UserDetailViewModel @Inject constructor(
    private val userDetailRepository: UserDetailRepository
) : ViewModel() {

    init {
        Timber.d("Inject UserDetailViewModel")
    }

    val message: MutableLiveData<String> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = userDetailRepository.isLoading

    fun fetchUserDetail(username: String): LiveData<UserDetail> {
        return userDetailRepository.fetchUserDetail(username) { message.postValue(it) }
    }
}
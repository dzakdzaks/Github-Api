package com.dzakdzaks.github_api.network.client

import com.dzakdzaks.github_api.entity.entities.UserDetail
import com.dzakdzaks.github_api.network.ApiResponse
import com.dzakdzaks.github_api.network.service.UserDetailService
import com.dzakdzaks.github_api.network.transform

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 06 March 2020 at 14:01.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network.client
 * ==================================//==================================
 * ==================================//==================================
 */
class UserDetailClient(
    private val service: UserDetailService
) {
    fun fetchUserDetail(
        username: String,
        onResult: (response: ApiResponse<UserDetail>) -> Unit
    ) {
        this.service.fetchUserDetail(username).transform(onResult)
    }
}
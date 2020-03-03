package com.dzakdzaks.github_api.network.client

import com.dzakdzaks.github_api.entity.response.SearchUsersResponse
import com.dzakdzaks.github_api.network.ApiResponse
import com.dzakdzaks.github_api.network.service.UserService
import com.dzakdzaks.github_api.network.transform

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:14.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network.client
 * ==================================//==================================
 * ==================================//==================================
 */
class UserClient(private val service: UserService) {

    fun fetchUsersResult(
        query: String,
        onResult: (response: ApiResponse<SearchUsersResponse>) -> Unit
    ) {
        this.service.searchUsers(query).transform(onResult)
    }

}
package com.dzakdzaks.github_api.network.client

import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.entity.response.UsersResponse
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

    fun fetchUsersResultSearch(
        query: String,
        page: Int,
        perPage: Int,
        onResult: (response: ApiResponse<UsersResponse>) -> Unit
    ) {
        this.service.searchUsers(query, page, perPage).transform(onResult)
    }

    fun fetchUserResult(
        onResult: (response: ApiResponse<List<Users>>) -> Unit
    ) {
        this.service.fetchUser().transform(onResult)
    }

    fun fetchUserResultPaginate(
        url: String,
        onResult: (response: ApiResponse<List<Users>>) -> Unit
    ) {
        this.service.fetchUserPaginate(url).transform(onResult)
    }

}
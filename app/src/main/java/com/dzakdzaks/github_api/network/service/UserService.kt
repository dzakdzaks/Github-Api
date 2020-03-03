package com.dzakdzaks.github_api.network.service

import com.dzakdzaks.github_api.entity.response.SearchUsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:14.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network.service
 * ==================================//==================================
 * ==================================//==================================
 */
interface UserService {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<SearchUsersResponse>
}
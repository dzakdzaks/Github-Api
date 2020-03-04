package com.dzakdzaks.github_api.network.service

import com.dzakdzaks.github_api.entity.entities.Users
import com.dzakdzaks.github_api.entity.response.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

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
    fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<UsersResponse>

    @GET("users")
    fun fetchUser(): Call<List<Users>>

    @GET
    fun fetchUserPaginate(
        @Url url: String
    ): Call<List<Users>>
}
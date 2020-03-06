package com.dzakdzaks.github_api.network.service

import com.dzakdzaks.github_api.entity.entities.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Friday, 06 March 2020 at 14:02.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network.service
 * ==================================//==================================
 * ==================================//==================================
 */
interface UserDetailService {

    @GET("users/{username}")
    fun fetchUserDetail(
        @Path("username") username: String
    ): Call<UserDetail>

}
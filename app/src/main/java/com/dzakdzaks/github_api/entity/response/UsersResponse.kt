package com.dzakdzaks.github_api.entity.response

import com.dzakdzaks.github_api.entity.entities.Users
import com.google.gson.annotations.SerializedName

data class UsersResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<Users> = emptyList()
) : NetworkResponse
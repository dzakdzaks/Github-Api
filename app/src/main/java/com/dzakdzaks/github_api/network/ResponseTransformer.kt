package com.dzakdzaks.github_api.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 14:49.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network
 * ==================================//==================================
 * ==================================//==================================
 */

/** transforms [Call] to [ApiResponse] via enqueueing response callback. */
fun <T> Call<T>.transform(onResult: (response: ApiResponse<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResult(ApiResponse.of { response })
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onResult(ApiResponse.error(t))
        }
    })
}

/** gets the [ApiResponse.Failure.Error] message with a error code. */
fun <ResponseClass> ApiResponse.Failure.Error<ResponseClass>.message() =
    "Error $code: ${responseBody?.string()}"

/** gets the [ApiResponse.Failure.Exception] message. */
fun <ResponseClass> ApiResponse.Failure.Exception<ResponseClass>.message() = "Exception: $message"

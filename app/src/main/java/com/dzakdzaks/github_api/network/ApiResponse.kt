package com.dzakdzaks.github_api.network

import okhttp3.ResponseBody
import retrofit2.Response

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 14:51.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network
 * ==================================//==================================
 * ==================================//==================================
 */

sealed class ApiResponse<out ResponseClass> {

    /**
     * API Success response class from retrofit.
     *
     * [data] is optional. (There are responses without data)
     */
    class Success<ResponseClass>(response: Response<ResponseClass>) : ApiResponse<ResponseClass>() {
        val data: ResponseClass? = response.body()
        //        val url: String? = sinceUsersCreator(response.headers()["Link"]!!)
        val url: String? =
            if (response.headers()["Link"] != null) response.headers()["Link"] else ""

        override fun toString(): String = "[ApiResponse.Success]: $data"
    }

    /**
     * API Failure response class.
     *
     * ## Throw Exception case.
     * Gets called when an unexpected exception occurs while creating the request or processing the response.
     *
     * ## API Network format error case.
     * API communication conventions do not match or applications need to handle errors.
     *
     * ## API Network Excepttion error case.
     * Gets called when an unexpected exception occurs while creating the request or processing the response.
     */
    sealed class Failure<out ResponseClass> {
        class Error<out ResponseClass>(response: Response<out ResponseClass>) :
            ApiResponse<ResponseClass>() {
            val responseBody: ResponseBody? = response.errorBody()?.apply { close() }
            val code: Int = response.code()
            override fun toString(): String =
                "[ApiResponse.Failure.Error $code]: ${responseBody?.string()}"
        }

        class Exception<out ResponseClass>(exception: Throwable) : ApiResponse<ResponseClass>() {
            val message: String? = exception.message
            override fun toString(): String = "[ApiResponse.Failure.Exception]: $message"
        }
    }

    companion object {
        /**
         * ApiResponse Factory
         *
         * [Failure] factory function. Only receives [Throwable] arguments.
         */
        fun <ResponseClass> error(exception: Throwable) =
            Failure.Exception<ResponseClass>(exception)

        /**
         * ApiResponse Factory
         *
         * [f] Create ApiResponse from [retrofit2.Response] returning from the block.
         * If [retrofit2.Response] has no errors, it will create [ApiResponse.Success]
         * If [retrofit2.Response] has errors, it will create [ApiResponse.Failure.Error]
         */
        fun <ResponseClass> of(f: () -> Response<ResponseClass>): ApiResponse<ResponseClass> = try {
            val response = f()
            if (response.isSuccessful) {
                Success(response)
            } else {
                Failure.Error(response)
            }
        } catch (exception: Exception) {
            Failure.Exception(exception)
        }
    }
}
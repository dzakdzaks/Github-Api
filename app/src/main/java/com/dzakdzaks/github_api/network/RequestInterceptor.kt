package com.dzakdzaks.github_api.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 10:20.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.network
 * ==================================//==================================
 * ==================================//==================================
 */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        /*QUERY PARAMETER*/
//        val originRequest = chain.request()
//        val originUrl = originRequest.url

        /*add query authorization to every single services*/
//        val url = originUrl.newBuilder()
//            .addQueryParameter("key", "val")
//            .build()
//        val requestBuilder = originRequest.newBuilder().url(url)
//        val request = requestBuilder.build()
//        return chain.proceed(request)

        /*HEADER PARAMETER*/
        val builder = chain.request().newBuilder()
        builder.addHeader("Authorization", "token c72f705716b11db409bbf7afd8404d1cdc8f6267")
        return chain.proceed(builder.build())
    }

    fun logging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}
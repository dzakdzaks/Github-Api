package com.dzakdzaks.github_api.di

import com.dzakdzaks.github_api.network.Endpoint
import com.dzakdzaks.github_api.network.RequestInterceptor
import com.dzakdzaks.github_api.network.client.UserClient
import com.dzakdzaks.github_api.network.client.UserDetailClient
import com.dzakdzaks.github_api.network.service.UserDetailService
import com.dzakdzaks.github_api.network.service.UserService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Tuesday, 03 March 2020 at 15:40.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.di
 * ==================================//==================================
 * ==================================//==================================
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(RequestInterceptor().logging())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Endpoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserClient(userService: UserService): UserClient {
        return UserClient(userService)
    }

    @Provides
    @Singleton
    fun provideUserDetailService(retrofit: Retrofit): UserDetailService {
        return retrofit.create(UserDetailService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDetailClient(userDetailService: UserDetailService): UserDetailClient {
        return UserDetailClient(userDetailService)
    }
}
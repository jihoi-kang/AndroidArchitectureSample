package com.jay.androidarchitecturesample.api

import com.jay.androidarchitecturesample.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val API_BASE_URL = "https://openapi.naver.com"

    val naverBookApiService: NaverBookApiService by lazy {
        retrofit.create(NaverBookApiService::class.java)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                        .build()
                )
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
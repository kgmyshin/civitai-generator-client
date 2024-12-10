package com.kgmyshin.civitai.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClientFactory {
    fun create(client: OkHttpClient = OkHttpClient()): ApiClient = ApiClient(
        Retrofit.Builder()
            .baseUrl("https://orchestration.civitai.com")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(client)
            .build().create(InnerApiClient::class.java)
    )
}
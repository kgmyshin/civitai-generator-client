package com.kgmyshin.civitai.api

import com.kgmyshin.civitai.api.json.CreateImageRequestJson
import com.kgmyshin.civitai.api.json.CreateImageResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByJobIdResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByTokenResponseJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClientFactory {
    fun create(client: OkHttpClient = OkHttpClient()): ApiClient = makeApiClient(client)

    internal fun makeApiClient(client: OkHttpClient): ApiClient {
        fun bearerToken(token: String): String = "Bearer $token"
        val innerApiClient = Retrofit.Builder()
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
        return object : ApiClient {
            override suspend fun createImage(
                accessToken: String,
                wait: Boolean,
                detailed: Boolean,
                whatif: Boolean,
                charge: Boolean,
                body: CreateImageRequestJson
            ): CreateImageResponseJson =
                innerApiClient.createImage(
                    bearerToken(accessToken), wait, detailed, whatif, charge, body
                )

            override suspend fun getJobsByToken(
                accessToken: String,
                token: String,
                wait: Boolean,
                detailed: Boolean
            ): GetJobsByTokenResponseJson =
                innerApiClient.getJobsByToken(
                    bearerToken(accessToken), token, wait, detailed
                )

            override suspend fun getJobsByJobId(
                accessToken: String,
                jobId: String,
                detailed: Boolean
            ): GetJobsByJobIdResponseJson =
                innerApiClient.getJobsByJobId(
                    bearerToken(accessToken), jobId, detailed
                )
        }
    }
}
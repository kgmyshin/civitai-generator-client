package com.kgmyshin.civitai.api

import com.kgmyshin.civitai.api.json.CreateImageRequestJson
import com.kgmyshin.civitai.api.json.CreateImageResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByJobIdResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByTokenResponseJson
import retrofit2.http.*


interface InnerApiClient {

    @POST("v1/consumer/jobs")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun createImage(
        @Header("Authorization") authorization: String,
        @Query("wait") wait: Boolean = false,
        @Query("detailed") detailed: Boolean = false,
        @Query("whatif") whatif: Boolean = false,
        @Query("charge") charge: Boolean = false,
        @Body body: CreateImageRequestJson
    ): CreateImageResponseJson

    @GET("v1/consumer/jobs")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun getJobsByToken(
        @Header("Authorization") authorization: String,
        @Query("token") token: String,
        @Query("wait") wait: Boolean = false,
        @Query("detailed") detailed: Boolean = false,
    ): GetJobsByTokenResponseJson

    @GET("v1/consumer/jobs/{jobId}")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun getJobsByJobId(
        @Header("Authorization") authorization: String,
        @Path("jobId") jobId: String,
        @Query("detailed") detailed: Boolean = false,
    ): GetJobsByJobIdResponseJson
}
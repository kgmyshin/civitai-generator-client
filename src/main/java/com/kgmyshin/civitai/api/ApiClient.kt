package com.kgmyshin.civitai.api

import com.kgmyshin.civitai.api.json.CreateImageRequestJson
import com.kgmyshin.civitai.api.json.CreateImageResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByJobIdResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByTokenResponseJson

class ApiClient(
    private val innerApiClient: InnerApiClient
) {
    suspend fun createImage(
        accessToken: String,
        wait: Boolean = true,
        detailed: Boolean = false,
        whatif: Boolean = false,
        charge: Boolean = false,
        body: CreateImageRequestJson
    ): CreateImageResponseJson = innerApiClient.createImage(
        bearerToken(accessToken), wait, detailed, whatif, charge, body
    )

    suspend fun getJobsByToken(
        accessToken: String,
        token: String,
        wait: Boolean = false,
        detailed: Boolean = false,
    ): GetJobsByTokenResponseJson = innerApiClient.getJobsByToken(
        bearerToken(accessToken), token, wait, detailed
    )

    suspend fun getJobsByJobId(
        accessToken: String,
        jobId: String,
        detailed: Boolean = false,
    ): GetJobsByJobIdResponseJson = innerApiClient.getJobsByJobId(
        bearerToken(accessToken), jobId, detailed
    )

    private fun bearerToken(token: String): String = "Bearer $token"
}
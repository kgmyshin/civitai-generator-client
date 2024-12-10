package com.kgmyshin.civitai.api

import com.kgmyshin.civitai.api.json.CreateImageRequestJson
import com.kgmyshin.civitai.api.json.CreateImageResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByJobIdResponseJson
import com.kgmyshin.civitai.api.json.GetJobsByTokenResponseJson

interface ApiClient {
    suspend fun createImage(
        accessToken: String,
        wait: Boolean = true,
        detailed: Boolean = false,
        whatif: Boolean = false,
        charge: Boolean = false,
        body: CreateImageRequestJson
    ): CreateImageResponseJson

    suspend fun getJobsByToken(
        accessToken: String,
        token: String,
        wait: Boolean = false,
        detailed: Boolean = false,
    ): GetJobsByTokenResponseJson

    suspend fun getJobsByJobId(
        accessToken: String,
        jobId: String,
        detailed: Boolean = false,
    ): GetJobsByJobIdResponseJson
}
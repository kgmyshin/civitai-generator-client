package com.kgmyshin.civitai

import com.kgmyshin.civitai.api.ApiClientFactory
import com.kgmyshin.civitai.api.json.CreateImageRequestJson
import com.kgmyshin.civitai.api.json.txt2img.BaseDiffusionModel
import com.kgmyshin.civitai.api.json.txt2img.ImageJobParams
import com.kgmyshin.civitai.api.json.txt2img.Scheduler
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Test

class CreateImageTextTest {

    @Test
    fun createImageTest() {
        val apiClient = ApiClientFactory.create(
            client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        val request = CreateImageRequestJson(
            quantity = 1,
            model = "urn:air:sd1:checkpoint:civitai:4384@128713",
            baseModel = BaseDiffusionModel.SDXL,
            additionalNetworks = null,
            params = ImageJobParams(
                prompt = "RAW photo, face portrait photo of woman, wearing black dress, happy face, hard shadows, cinematic shot, dramatic lighting",
                negativePrompt = "(deformed, distorted, disfigured:1.3)",
                scheduler = Scheduler.EulerA,
                steps = 20,
                cfgScale = 7.0,
                width = 512,
                height = 512,
                seed = null,
                clipSkip = 2
            ),
        )
        runBlocking {
            val createImageResponse = apiClient.createImage(
                System.getenv("CIVITAI_TOKEN"),
                wait = false,
                body = request
            )
            println(createImageResponse)

            val getJobsByTokenResponse = apiClient.getJobsByToken(
                System.getenv("CIVITAI_TOKEN"),
                token = createImageResponse.token
            )
            println(getJobsByTokenResponse)

            val getJobsResponseJson = apiClient.getJobsByJobId(
                System.getenv("CIVITAI_TOKEN"),
                jobId = createImageResponse.jobs.first().jobId
            )
            println(getJobsResponseJson)
        }
    }
}
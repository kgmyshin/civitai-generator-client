package com.kgmyshin.civitai

import com.kgmyshin.civitai.api.ApiClientFactory
import com.kgmyshin.civitai.api.json.CreateImageRequestJson
import com.kgmyshin.civitai.api.json.txt2img.AdditionalNetwork
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
            model = "urn:air:sdxl:checkpoint:civitai:372465@914390",
            baseModel = BaseDiffusionModel.SDXL,
            additionalNetworks = mapOf(
                "urn:air:sd1:lora:civitai:676511@757309" to AdditionalNetwork(
                    type = "Lora", strength = 0.2
                ), "urn:air:sd1:lora:civitai:122359@135867" to AdditionalNetwork(
                    type = "Lora", strength = 1.0
                ), "urn:air:sd1:lora:civitai:694900@777665" to AdditionalNetwork(
                    type = "Lora", strength = 1.0
                )
            ),
            params = ImageJobParams(
                prompt = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, 2d, sexy boudoir photography by Hajime Sorayama, blurry background, VindictusFiona, parted lips,Photography,masterpiece,ultra detailed,4k,professional artwork,official art, (source_photo),   absurdres, best quality, expressive, cinematic, (very Soft hue), Soft shadow,  seductive, vulgarity,obscenity,erotic, girl is cosplaying (), kpop idol, pouty lips,  female beautiful white skin, perfect hairline, long eyelash, (large eyes),  (bangs:1.2), (short Sleek hair cut:1.2), (black hair:1.2), (bangs:1.2), dark eyes (solo, 1girl, solo focus:1.1), (large breast), pale skin, creamy smooth skin,  wide hips, huge hips, anatomically correct, open mouth, silly smile, Expressiveh,  half-closed eyes,  obedient, ((satisfied expression)),  TONGUE OUT, ruanyi0897, black thighhighs, turtleneck, id card,lanyard,grey skirt,crop top,(black sweater:1.2), pencil skirt, Gray skirt,wide shot, side view, on left, medium shot, full body shot, on the street, sunrise, in the city, with shooting stars, at night, cardigan",
                negativePrompt = "ugly face,",
                scheduler = Scheduler.DPM2AKarras,
                steps = 30,
                cfgScale = 8.0,
                width = 1024,
                height = 1024,
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
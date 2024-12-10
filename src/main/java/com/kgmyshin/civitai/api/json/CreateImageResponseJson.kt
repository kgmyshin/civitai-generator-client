package com.kgmyshin.civitai.api.json

import com.kgmyshin.civitai.api.json.txt2img.Txt2ImgJobStatus
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateImageResponseJson(
    val token: String,
    val jobs: List<Txt2ImgJobStatus>
)






package com.kgmyshin.civitai.api.json.txt2img

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageJobParams(
    val prompt: String?,
    val negativePrompt: String?,
    val scheduler: Scheduler?,
    val steps: Int?,
    val cfgScale: Double?,
    val width: Int,
    val height: Int,
    val seed: Int?,
    val clipSkip: Int?
)
package com.kgmyshin.civitai.api.json.txt2img

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalNetwork(
    val type: String,
    val strength: Double
)
package com.kgmyshin.civitai.api.json

import com.kgmyshin.civitai.api.json.txt2img.AdditionalNetwork
import com.kgmyshin.civitai.api.json.txt2img.BaseDiffusionModel
import com.kgmyshin.civitai.api.json.txt2img.ImageJobParams
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateImageRequestJson(
    @Json(name = "\$type") val type: String = "textToImage",
    val model: String,
    val baseModel: BaseDiffusionModel,
    val quantity: Int,
    val additionalNetworks: Map<String, AdditionalNetwork>?,
    val params: ImageJobParams?,
)


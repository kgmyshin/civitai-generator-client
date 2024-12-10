package com.kgmyshin.civitai.api.json.txt2img

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val blobKey: String?,
    val available: Boolean?,
    val blobUrl: String?,
    val blobUrlExpirationDate: String?
)
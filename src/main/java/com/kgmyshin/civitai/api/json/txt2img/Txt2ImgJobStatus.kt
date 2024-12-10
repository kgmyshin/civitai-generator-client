package com.kgmyshin.civitai.api.json.txt2img

import com.kgmyshin.civitai.api.json.base.JobEvent
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Txt2ImgJobStatus(
    val jobId: String,
    val cost: Double,
    val properties: Map<String, String>?,
    val result: Result?,
    val lastEvent: JobEvent?,
    val serviceProviders: Map<String, Any>,
    val scheduled: Boolean
)
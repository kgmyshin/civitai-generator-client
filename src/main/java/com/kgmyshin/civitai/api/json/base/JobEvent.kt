package com.kgmyshin.civitai.api.json.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobEvent(
    val jobId: String?,
    val type: JobEventType?,
    val dateTime: String?,
    val provider: Provider?,
    val workerId: String?,
    val context: Map<String, Any>?,
    val claimDuration: String?,
    val jobDuration: String?,
    val retryAttempt: Int?,
    val cost: Float?,
    val jobProperties: Map<String, Any>?,
    val jobType: String?,
    val jobPriority: Int?,
    val claimHasCompleted: Boolean?,
    val jobHasCompleted: Boolean?
)

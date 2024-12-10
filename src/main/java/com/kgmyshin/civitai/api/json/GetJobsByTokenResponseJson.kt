package com.kgmyshin.civitai.api.json

import com.kgmyshin.civitai.api.json.txt2img.Txt2ImgJobStatus

data class GetJobsByTokenResponseJson(
    val token: String?,
    val jobs: List<Txt2ImgJobStatus>
)
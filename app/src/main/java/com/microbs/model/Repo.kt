package com.microbs.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)
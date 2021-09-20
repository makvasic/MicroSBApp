package com.microbs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "retro")
@JsonClass(generateAdapter = true)
data class Retro(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Json(name = "id") val id: Int,
    @ColumnInfo(name = "name")
    @Json(name = "name") val name: String
)
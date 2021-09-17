package com.microbs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId") val userId: Long = 0L,
    @ColumnInfo(name = "username") val username: String = "",
    @ColumnInfo(name = "password") val password: String = ""
)
package com.microbs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage")
data class Storage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "storageId") val storageId: Long = 0L,
    @ColumnInfo(name = "name") val name: String = ""
)
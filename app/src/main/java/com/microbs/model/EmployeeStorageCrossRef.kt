package com.microbs.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["employeeId", "storageId"])
data class EmployeeStorageCrossRef(
    @ColumnInfo(name = "employeeId") val employeeId: Long,
    @ColumnInfo(name = "storageId") val storageId: Long
)
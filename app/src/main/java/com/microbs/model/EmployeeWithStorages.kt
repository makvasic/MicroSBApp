package com.microbs.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EmployeeWithStorages(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "employeeId",
        entityColumn = "storageId",
        associateBy = Junction(EmployeeStorageCrossRef::class)
    )
    val storages: List<Storage>
)
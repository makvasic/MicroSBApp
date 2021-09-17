package com.microbs.model

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithCustomers(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "employeeId",
        entityColumn = "employeeId"
    )
    val customers: List<Customer>
)
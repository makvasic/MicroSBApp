package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import com.microbs.model.EmployeeStorageCrossRef

@Dao
interface EmployeeStorageCrossRefDao {

    @Insert
    fun insert(employeeStorageCrossRefs: List<EmployeeStorageCrossRef>)

}
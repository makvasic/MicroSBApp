package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.microbs.model.EmployeeStorageCrossRef

@Dao
interface EmployeeStorageCrossRefDao {

    @Insert
    fun insert(employeeStorageCrossRefs: List<EmployeeStorageCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForEmployee(employeeStorageCrossRefs: List<EmployeeStorageCrossRef>)
}
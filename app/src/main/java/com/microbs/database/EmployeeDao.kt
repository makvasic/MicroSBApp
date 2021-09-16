package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.microbs.model.Employee

@Dao
interface EmployeeDao {

    @Insert
    fun insert(employees: List<Employee>)

    @Query("SELECT * FROM employee WHERE userId = :userId")
    suspend fun getEmployees(userId: Long): List<Employee>
}
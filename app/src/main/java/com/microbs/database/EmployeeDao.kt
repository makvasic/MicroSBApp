package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.microbs.model.Employee
import com.microbs.model.EmployeeWithCustomers
import com.microbs.model.EmployeeWithStorages

@Dao
interface EmployeeDao {

    @Insert
    fun insert(employees: List<Employee>)

    @Transaction
    @Query("SELECT * FROM employee WHERE userId = :userId")
    suspend fun getEmployeesWithStorages(userId: Long): List<EmployeeWithStorages>

    @Transaction
    @Query("SELECT * FROM employee WHERE userId = :userId")
    suspend fun getEmployeesWithCustomers(userId: Long): List<EmployeeWithCustomers>
}
package com.microbs.database

import androidx.room.*
import com.microbs.model.Employee
import com.microbs.model.EmployeeWithCustomers
import com.microbs.model.EmployeeWithStorages

@Dao
interface EmployeeDao {

    @Insert
    fun insert(employees: List<Employee>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployee(employee: Employee): Long

    @Transaction
    @Query("SELECT * FROM employee WHERE userId = :userId")
    suspend fun getEmployeesWithStorages(userId: Long): List<EmployeeWithStorages>

    @Transaction
    @Query("SELECT * FROM employee WHERE employeeId = :employeeId")
    suspend fun getEmployeeWithStorages(employeeId: Long): EmployeeWithStorages

    @Transaction
    @Query("SELECT * FROM employee WHERE userId = :userId")
    suspend fun getEmployeesWithCustomers(userId: Long): List<EmployeeWithCustomers>
}
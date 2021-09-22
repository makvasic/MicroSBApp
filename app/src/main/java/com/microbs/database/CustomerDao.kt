package com.microbs.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.microbs.model.Customer

@Dao
interface CustomerDao {

    @Insert
    fun insert(customers: List<Customer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)
}
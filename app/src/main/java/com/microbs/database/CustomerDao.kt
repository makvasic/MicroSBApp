package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import com.microbs.model.Customer

@Dao
interface CustomerDao {

    @Insert
    fun insert(customers: List<Customer>)
}
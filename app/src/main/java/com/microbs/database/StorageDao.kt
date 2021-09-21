package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.microbs.model.Storage

@Dao
interface StorageDao {

    @Insert
    fun insert(storages: List<Storage>)

    @Query("SELECT * FROM storage")
    suspend fun getAll(): List<Storage>
}
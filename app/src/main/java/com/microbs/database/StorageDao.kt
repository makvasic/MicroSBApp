package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import com.microbs.model.Storage

@Dao
interface StorageDao {

    @Insert
    fun insert(storages: List<Storage>)
}
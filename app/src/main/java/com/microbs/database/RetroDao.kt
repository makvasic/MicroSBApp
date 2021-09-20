package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.microbs.model.Retro

@Dao
interface RetroDao {

    @Insert
    suspend fun insert(retros: List<Retro>)

    @Query("SELECT count(*) FROM retro")
    suspend fun getRetrosCount(): Int

    @Query("SELECT * FROM retro")
    suspend fun getRetros(): List<Retro>
}
package com.microbs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.microbs.model.User

@Dao
interface UserDao {

    @Insert
    fun insertDefault(user: User)

    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?
}
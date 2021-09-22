package com.microbs.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customerId") val customerId: Long = 0L,
    @ColumnInfo(name = "userId") val userId: Long = 0L,
    @ColumnInfo(name = "employeeId") val employeeId: Long = 0L,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "pib") var pib: String = ""
) : Parcelable
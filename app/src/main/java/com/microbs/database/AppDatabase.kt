package com.microbs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.microbs.model.*
import java.util.concurrent.Executors

@Database(
    entities = [User::class, Employee::class, Customer::class, Storage::class, EmployeeStorageCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storageDao(): StorageDao
    abstract fun userDao(): UserDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun customerDao(): CustomerDao
    abstract fun employeeStorageCrossRefDao(): EmployeeStorageCrossRefDao


    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "microbs.db"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {

                        INSTANCE!!.userDao().insertDefault(
                            User(0L, "makvasic", "123456789")
                        )

                        INSTANCE!!.employeeDao().insert(
                            arrayListOf(
                                Employee(0L, 1, "Zaposleni 1", "Kragujevac"),
                                Employee(0L, 1, "Zaposleni 2", "Beograd")
                            )
                        )

                        INSTANCE!!.storageDao().insert(
                            arrayListOf(
                                Storage(0L, "Magacin 1"),
                                Storage(0L, "Magacin 2"),
                                Storage(0L, "Magacin 3"),
                                Storage(0L, "Magacin 4"),
                                Storage(0L, "Magacin 5"),
                                Storage(0L, "Magacin 6"),
                                Storage(0L, "Magacin 7"),
                                Storage(0L, "Magacin 8"),
                                Storage(0L, "Magacin 9"),
                                Storage(0L, "Magacin 10")
                            )
                        )

                        INSTANCE!!.employeeStorageCrossRefDao().insert(
                            arrayListOf(
                                EmployeeStorageCrossRef(1L, 1L),
                                EmployeeStorageCrossRef(1L, 5L),
                                EmployeeStorageCrossRef(1L, 8L),
                                EmployeeStorageCrossRef(1L, 10L),
                            )
                        )

                        INSTANCE!!.customerDao().insert(
                            arrayListOf(
                                Customer(0L, 1L, 1L, "Kupac 1", "bip bip 1"),
                                Customer(0L, 1L, 1L, "Kupac 2", "bip bip 2")
                            )
                        )
                    }
                }
            }).build()
        }
    }
}
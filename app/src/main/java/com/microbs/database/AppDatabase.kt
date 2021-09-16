package com.microbs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.microbs.model.Customer
import com.microbs.model.Employee
import com.microbs.model.Storage
import com.microbs.model.User
import java.util.concurrent.Executors

@Database(
    entities = [User::class, Employee::class, Customer::class, Storage::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storageDao(): StorageDao
    abstract fun userDao(): UserDao
    abstract fun employeeDao(): EmployeeDao


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
                        INSTANCE!!.storageDao().insert(
                            arrayListOf(
                                Storage(name = "Magacin 1"),
                                Storage(name = "Magacin 2"),
                                Storage(name = "Magacin 3"),
                                Storage(name = "Magacin 4"),
                                Storage(name = "Magacin 5"),
                                Storage(name = "Magacin 6"),
                                Storage(name = "Magacin 7"),
                                Storage(name = "Magacin 8"),
                                Storage(name = "Magacin 9"),
                                Storage(name = "Magacin 10")
                            )
                        )

                        INSTANCE!!.employeeDao().insert(
                            arrayListOf(
                                Employee(0L, 1, "Mak Employee 1", "Kragujevac"),
                                Employee(0L, 1, "Sava Employee 2", "Beograd")
                            )
                        )
                    }
                }
            }).build()
        }
    }
}
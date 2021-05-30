package com.example.employeedetail.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeListDao(): EmployeeListDao
    abstract fun employeeDetailDao(): EmployeeDetailDao

    companion object {

        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(
            context: Context
        ): EmployeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employee_database"
                )
                    .build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }

}
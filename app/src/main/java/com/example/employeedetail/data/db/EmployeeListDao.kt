package com.example.employeedetail.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmployeeListDao {

    @Query("SELECT * FROM Employee ORDER BY name")
    fun getAllEmployee(): LiveData<List<Employee>>

}
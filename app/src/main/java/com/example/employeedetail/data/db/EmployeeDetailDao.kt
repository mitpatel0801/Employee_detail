package com.example.employeedetail.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Query("SELECT * FROM employee WHERE `id`= :id")
     fun getEmployee(id: Long): LiveData<Employee>

}
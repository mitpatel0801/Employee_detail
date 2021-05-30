package com.example.employeedetail.data.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.employeedetail.data.db.Employee
import com.example.employeedetail.data.db.EmployeeDatabase

class EmployeeDetailRepository(application: Application) {

    private val employeeDetailDao = EmployeeDatabase.getDatabase(application).employeeDetailDao()

    suspend fun insertEmployee(employee: Employee) {
        employeeDetailDao.insertEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDetailDao.deleteEmployee(employee)
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDetailDao.updateEmployee(employee)
    }

     fun getEmployee(id: Long):LiveData<Employee>  {
        return employeeDetailDao.getEmployee(id)
    }

}
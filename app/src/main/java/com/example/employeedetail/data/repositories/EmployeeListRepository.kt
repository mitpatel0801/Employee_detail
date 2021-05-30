package com.example.employeedetail.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.employeedetail.data.db.Employee
import com.example.employeedetail.data.db.EmployeeDatabase

class EmployeeListRepository(context: Context) {

    private val employeeListDao = EmployeeDatabase.getDatabase(context).employeeListDao()

    fun getEmployeeList():LiveData<List<Employee>>
    {
        return employeeListDao.getAllEmployee()
    }


}
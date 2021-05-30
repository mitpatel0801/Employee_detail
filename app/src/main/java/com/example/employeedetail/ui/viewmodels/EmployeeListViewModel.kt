package com.example.employeedetail.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.employeedetail.data.db.Employee
import com.example.employeedetail.data.repositories.EmployeeListRepository

class EmployeeListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = EmployeeListRepository(application)
    val employeeList:LiveData<List<Employee>> = repo.getEmployeeList()


}
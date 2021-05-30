package com.example.employeedetail.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.employeedetail.data.db.Employee
import com.example.employeedetail.data.repositories.EmployeeDetailRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = EmployeeDetailRepository(application)

    private val _id: MutableLiveData<Long> = MutableLiveData(0L)
    val id get() = _id

    val employee: LiveData<Employee> = Transformations.switchMap(_id) {
        repo.getEmployee(it)
    }

    fun setNewId(id: Long) {
        if (_id.value != id) {
            _id.value = id
        }
    }


    fun save(employee: Employee) {
        viewModelScope.launch {
            if (employee.id == 0L) {
                repo.insertEmployee(employee)
            } else {
                repo.updateEmployee(employee)
            }
        }
    }

    fun deleteEmployee() {
        viewModelScope.launch {
            employee.value?.let { repo.deleteEmployee(it) }
        }
    }
}
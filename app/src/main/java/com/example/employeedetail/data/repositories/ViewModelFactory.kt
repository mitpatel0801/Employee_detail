package com.example.employeedetail.data.repositories

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeedetail.ui.viewmodels.EmployeeDetailViewModel
import com.example.employeedetail.ui.viewmodels.EmployeeListViewModel
import java.lang.IllegalArgumentException

open class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(EmployeeDetailViewModel::class.java) -> {
                EmployeeDetailViewModel(application) as T
            }
            modelClass.isAssignableFrom(EmployeeListViewModel::class.java) -> {
                EmployeeListViewModel(application) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModelClass not found")
            }
        }
    }
}
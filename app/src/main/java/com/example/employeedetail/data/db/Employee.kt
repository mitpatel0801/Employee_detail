package com.example.employeedetail.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Gender{
    Male,
    Female,
    Other
}

enum class Role{
    Manager,
    Staff,
    Worker
}


@Entity
data class Employee
    (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val role: Int,
    val age: Int,
    val gender: Int,
    val photo: String
)
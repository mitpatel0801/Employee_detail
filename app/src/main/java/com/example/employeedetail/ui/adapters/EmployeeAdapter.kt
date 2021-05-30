package com.example.employeedetail.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetail.data.db.Employee
import com.example.employeedetail.data.db.Gender
import com.example.employeedetail.data.db.Role
import com.example.employeedetail.databinding.ListItemBinding


class EmployeeAdapter(val context: Context, val listener: (Long) -> Unit) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    private val employeeList = mutableListOf<Employee>()

    inner class ViewHolder(private val item: ListItemBinding) :
        RecyclerView.ViewHolder(item.root) {

        //item click listener
        init {
            itemView.setOnClickListener {
                listener(employeeList[absoluteAdapterPosition].id)
            }
        }

        //data binding
        fun bind(employee: Employee) {
            with(item)
            {

                employeeName.text = employee.name

                employeeAge.text =  "${employee.age} Years"

                employeeGender.text = setGender(employee.gender)

                employeeRole.text = setRole(employee.role)
            }
        }
    }

    //Setting gender String Value for its enum ordinal value
    private fun setGender(gender: Int): String {
        return when (gender) {
            Gender.Female.ordinal -> Gender.Female.toString()
            Gender.Male.ordinal -> Gender.Male.toString()
            Gender.Other.ordinal -> Gender.Other.toString()
            else -> ""
        }
    }

    //Setting role String Value for its enum ordinal value
    private fun setRole(role: Int): String {
        return when (role) {
            Role.Manager.ordinal -> Role.Manager.toString()
            Role.Staff.ordinal -> Role.Staff.toString()
            Role.Worker.ordinal -> Role.Worker.toString()
            else -> ""
        }
    }

    //Creating View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    //Binding View
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    override fun getItemCount(): Int = employeeList.size

    //Setting List to Show
    fun setList(newEmployeeList: List<Employee>) {
        employeeList.clear()
        employeeList.addAll(newEmployeeList)
        notifyDataSetChanged()
    }
}
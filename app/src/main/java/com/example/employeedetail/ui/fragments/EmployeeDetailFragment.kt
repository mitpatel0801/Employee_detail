package com.example.employeedetail.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.example.employeedetail.R
import com.example.employeedetail.data.db.Employee
import com.example.employeedetail.data.db.Gender
import com.example.employeedetail.data.db.Role
import com.example.employeedetail.data.repositories.ViewModelFactory
import com.example.employeedetail.databinding.FragmentEmployeeDetailBinding
import com.example.employeedetail.ui.viewmodels.EmployeeDetailViewModel

class EmployeeDetailFragment : Fragment() {

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EmployeeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //view model initialization
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity().application)
        )[EmployeeDetailViewModel::class.java]

        //Binding
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = EmployeeDetailFragmentArgs.fromBundle(requireArguments()).id
        viewModel.setNewId(id)

        with(binding) {

            //Age Spinner Setup
            employeeAge.adapter = ArrayAdapter(
                this@EmployeeDetailFragment.requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                (18..65).toList()
            )

            //Role Spinner Setup
            val roleList = Role.values().toList()

            employeeRole.adapter = ArrayAdapter(
                this@EmployeeDetailFragment.requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                roleList
            )

            //Save Employee Button Click Listener
            saveEmployee.setOnClickListener {
                saveEmployee()
            }

            //Delete Employee Button Click Listener
            deleteEmployee.setOnClickListener {
                deleteEmployee()
            }

        }

        viewModel.employee.observe(viewLifecycleOwner, {
            setUpEmployee(it)
        })

    }

    //Employee Setup
    private fun setUpEmployee(employee: Employee?) {
        employee?.let {
            with(binding)
            {
                employeeName.setText(employee.name)
                employeeRole.setSelection(employee.role)
                employeeAge.setSelection(employee.age-18)
                when (employee.gender) {
                    Gender.Female.ordinal -> genderFemale.isChecked = true
                    Gender.Male.ordinal -> genderMale.isChecked = true
                    Gender.Other.ordinal -> genderOther.isChecked = true
                }
            }
        }

    }



    //Deleting Employee
    private fun deleteEmployee() {
        viewModel.deleteEmployee()
        requireActivity().onBackPressed()
    }


    //Saving Employee
    private fun saveEmployee() {
        with(binding) {

            val name = employeeName.text.toString()
            val role = employeeRole.selectedItemPosition
            val ageValue = employeeAge.selectedItem
            val age = ageValue?.toString()?.toInt() ?: 18
            val gender =
                when (ganderGroup.findViewById<RadioButton>(ganderGroup.checkedRadioButtonId).text) {
                    Gender.Other.name -> Gender.Other.ordinal
                    Gender.Male.name -> Gender.Male.ordinal
                    Gender.Female.name -> Gender.Female.ordinal
                    else -> -1
                }
            val employee = Employee(viewModel.id.value!!, name, role, age, gender, "")
            viewModel.save(employee)
        }

        requireActivity().onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
package com.example.employeedetail.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedetail.data.repositories.ViewModelFactory
import com.example.employeedetail.databinding.FragmentEmployeeListBinding
import com.example.employeedetail.ui.adapters.EmployeeAdapter
import com.example.employeedetail.ui.viewmodels.EmployeeListViewModel

class EmployeeListFragment : Fragment() {

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewMode: EmployeeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //New View Model
        viewMode = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity().application)
        )[EmployeeListViewModel::class.java]

        //Binding
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Add new Employee
        binding.addEmployee.setOnClickListener {
            findNavController().navigate(
                EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment(
                    0L
                )
            )
        }

        //Adapter And Click Listener for item View
        val newAdapter = EmployeeAdapter(view.context) { id ->
            findNavController().navigate(
                EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment(
                    id
                )
            )
        }

        //RecycleView
        binding.employeeList.layoutManager = LinearLayoutManager(activity)
        binding.employeeList.adapter = newAdapter


        //Live Data for Adapter List
        viewMode.employeeList.observe(viewLifecycleOwner, {
            newAdapter.setList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
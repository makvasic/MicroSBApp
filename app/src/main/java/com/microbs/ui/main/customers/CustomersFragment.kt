package com.microbs.ui.main.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.microbs.databinding.FragmentCustomersBinding
import com.microbs.model.EmployeeWithCustomers
import com.microbs.ui.main.MainViewModel

class CustomersFragment : Fragment() {

    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        CustomersAdapter {
            // TODO: 20.9.2021. Start customerFragment
        }
    }
    private val spinnerAdapter by lazy { EmployeesSpinnerAdapter(requireContext()) }
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val customers =
                    (binding.spinner.getItemAtPosition(position) as EmployeeWithCustomers).customers
                adapter.submitList(customers)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        mainViewModel.employeesWithCustomersLiveData.observe(viewLifecycleOwner) { employeesWithCustomers ->
            if (employeesWithCustomers.isEmpty()) {
                Toast.makeText(context, "Please add some employees first!", Toast.LENGTH_SHORT)
                    .show()
                binding.fab.hide()
                return@observe
            }

            spinnerAdapter.setData(employeesWithCustomers)
            binding.fab.show()
        }

        mainViewModel.getEmployeesWithCustomers()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "CustomersFragment"

        fun newInstance() = CustomersFragment()
    }
}
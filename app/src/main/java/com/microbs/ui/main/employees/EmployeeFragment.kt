package com.microbs.ui.main.employees

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.microbs.R
import com.microbs.databinding.FragmentEmployeeBinding
import com.microbs.model.Employee
import com.microbs.model.EmployeeWithStorages
import com.microbs.ui.Repository
import com.microbs.ui.main.MainViewModel

class EmployeeFragment : Fragment() {

    private var _binding: FragmentEmployeeBinding? = null
    private val binding get() = _binding!!

    private val employeeViewModel: EmployeeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val adapter: StoragesAdapter by lazy { StoragesAdapter() }
    private var employeeId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        arguments?.let {
            employeeId = it.getLong("employee_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            showAddStoragesToEmployeeDialog()
        }

        employeeViewModel.employeeWithStoragesLiveData.observe(viewLifecycleOwner) { employeeWithStorages ->
            if (employeeWithStorages == null) return@observe
            binding.employeeNameEditText.setText(employeeWithStorages.employee.name)
            binding.employeeCityEditText.setText(employeeWithStorages.employee.city)

            adapter.submitList(employeeWithStorages.storages)
        }

        mainViewModel.storagesLiveData.observe(viewLifecycleOwner) { storages ->

            if (storages == null) {
                return@observe
            }

            val items = storages.map { it.name }.toTypedArray()
            val storageIds = adapter.currentList.map { it.storageId }.toLongArray()

            AddStoragesToEmployeeDialog.newInstance(employeeId, storageIds, items)
                .show(childFragmentManager, null)

            mainViewModel.storagesLiveData.value = null
        }

        employeeViewModel.getEmployeeWithStorages(employeeId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItemConfirm) {
            saveEmployeeWithStorages()
            requireActivity().onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAddStoragesToEmployeeDialog() {
        mainViewModel.getStorages()
    }

    private fun saveEmployeeWithStorages() {
        val name = binding.employeeNameEditText.text.toString()
        val city = binding.employeeCityEditText.text.toString()

        val employeeWithStorages = EmployeeWithStorages(
            Employee(employeeId, Repository.userId, name, city),
            adapter.currentList
        )

        mainViewModel.saveEmployeeWithStorages(employeeWithStorages)

    }

    companion object {
        fun newInstance(employeeId: Long = 0L): EmployeeFragment {
            val args = Bundle()
            args.putLong("employee_id", employeeId)
            val fragment = EmployeeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
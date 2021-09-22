package com.microbs.ui.main.employees

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.microbs.model.EmployeeStorageCrossRef

class AddStoragesToEmployeeDialog : DialogFragment() {

    private val employeeViewModel: EmployeeViewModel by viewModels({ requireParentFragment() })

    private var employeeId = 0L
    private var storageIds = LongArray(10)
    private var items = Array(10) { "" }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            employeeId = it.getLong("employee_id")
            storageIds = it.getLongArray("storage_ids") ?: LongArray(10)
            items = it.getStringArray("items") as Array<String>
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val storageIdsToInsert = storageIds.toHashSet()
        val storageIdsToDelete = HashSet<Long>()

        val checkedItems = BooleanArray(10) { false }
        for (i in 1..10) checkedItems[i - 1] = storageIds.contains(i.toLong())

        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Add storages for employee")
            .setMultiChoiceItems(items, checkedItems) { _, which, isChecked ->
                val storageId = which + 1L
                if (isChecked) {
                    storageIdsToInsert.add(storageId)
                    storageIdsToDelete.remove(storageId)
                } else {
                    storageIdsToInsert.remove(storageId)
                    storageIdsToDelete.add(storageId)
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .setPositiveButton("Add") { _, _ ->
                updateStoragesForEmployee(employeeId, storageIdsToInsert, storageIdsToDelete)
                dismiss()
            }


        return builder.show()

    }

    private fun updateStoragesForEmployee(
        employeeId: Long,
        storageIdsToInsert: HashSet<Long>,
        storageIdsToDelete: HashSet<Long>
    ) {
        val refsToInsert = ArrayList<EmployeeStorageCrossRef>()
        val refsToDelete = ArrayList<EmployeeStorageCrossRef>()
        storageIdsToInsert.forEach { storageId ->
            refsToInsert.add(EmployeeStorageCrossRef(employeeId, storageId))
        }
        storageIdsToDelete.forEach { storageId ->
            refsToDelete.add(EmployeeStorageCrossRef(employeeId, storageId))
        }

        employeeViewModel.updateEmployeeStorages(employeeId, refsToInsert, refsToDelete)
    }

    companion object {
        fun newInstance(
            employeeId: Long,
            storageIds: LongArray,
            items: Array<String>
        ): AddStoragesToEmployeeDialog {
            val args = Bundle()
            args.putLong("employee_id", employeeId)
            args.putLongArray("storage_ids", storageIds)
            args.putStringArray("items", items)
            val fragment = AddStoragesToEmployeeDialog()
            fragment.arguments = args
            return fragment
        }
    }
}
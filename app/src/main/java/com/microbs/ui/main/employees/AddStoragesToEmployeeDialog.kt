package com.microbs.ui.main.employees

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.microbs.model.EmployeeStorageCrossRef
import com.microbs.ui.main.MainViewModel

class AddStoragesToEmployeeDialog : DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val employeeViewModel: EmployeeViewModel by viewModels({ requireParentFragment() })

    private var employeeId = 0L
    private var storageIds = HashSet<Long>()
    private var items = Array(10) { "" }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            employeeId = it.getLong("employee_id")
            storageIds = it.getLongArray("storage_ids")?.toHashSet() ?: HashSet()
            items = it.getStringArray("items") as Array<String>
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val checkedItems = BooleanArray(10) { false }
        for (i in 1..10) checkedItems[i - 1] = storageIds.contains(i.toLong())

        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Add storages for employee")
            .setMultiChoiceItems(items, checkedItems) { _, which, isChecked ->
                if (isChecked) {
                    storageIds.add(which + 1L)
                } else {
                    storageIds.remove(which + 1L)
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .setPositiveButton("Add") { _, _ ->
                addStoragesToEmployee(employeeId, storageIds)
                dismiss()
            }


        return builder.show()

    }

    private fun addStoragesToEmployee(
        employeeId: Long,
        storageIds: HashSet<Long>
    ) {
        val employeeStorageCrossRefs = ArrayList<EmployeeStorageCrossRef>()
        storageIds.forEach { storageId ->
            employeeStorageCrossRefs.add(EmployeeStorageCrossRef(employeeId, storageId))
        }

        mainViewModel.addStoragesToEmployee(employeeStorageCrossRefs)
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
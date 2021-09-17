package com.microbs.ui.main.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.microbs.R
import com.microbs.databinding.ItemEmployeeBinding
import com.microbs.model.EmployeeWithStorages

class EmployeesAdapter : ListAdapter<EmployeeWithStorages, EmployeesAdapter.EmployeeHolder>(object :
    DiffUtil.ItemCallback<EmployeeWithStorages>() {
    override fun areItemsTheSame(
        oldItem: EmployeeWithStorages,
        newItem: EmployeeWithStorages
    ): Boolean {
        return oldItem.employee.employeeId == newItem.employee.employeeId
    }

    override fun areContentsTheSame(
        oldItem: EmployeeWithStorages,
        newItem: EmployeeWithStorages
    ): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        return EmployeeHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EmployeeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EmployeeWithStorages) {
            val binding = ItemEmployeeBinding.bind(itemView)

            binding.employeeTextView.text = item.toString()
        }
    }
}

package com.microbs.ui.main.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.microbs.R
import com.microbs.databinding.ItemEmployeeBinding
import com.microbs.model.Employee

class EmployeesAdapter : ListAdapter<Employee, EmployeesAdapter.EmployeeHolder>(object :
    DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(
        oldItem: Employee,
        newItem: Employee
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Employee,
        newItem: Employee
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
        fun bind(item: Employee) {
            val binding = ItemEmployeeBinding.bind(itemView)

            binding.employeeTextView.text = item.toString()
        }
    }
}

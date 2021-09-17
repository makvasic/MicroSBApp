package com.microbs.ui.main.customers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.microbs.model.EmployeeWithCustomers

class EmployeesSpinnerAdapter(
    context: Context,
    private val items: ArrayList<EmployeeWithCustomers> = ArrayList()
) : ArrayAdapter<EmployeeWithCustomers>(
    context,
    android.R.layout.simple_spinner_item,
    items
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getSpinnerView(position, convertView, parent, android.R.layout.simple_spinner_item)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getSpinnerView(
            position,
            convertView,
            parent,
            android.R.layout.simple_spinner_dropdown_item
        )
    }

    private fun getSpinnerView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
        @LayoutRes resId: Int
    ): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(resId, parent, false)

        val textView: TextView = view as TextView

        val item: EmployeeWithCustomers? = getItem(position)
        textView.text = item?.employee?.name

        return view
    }

    fun setData(employeesWithCustomers: List<EmployeeWithCustomers>) {
        items.clear()
        items.addAll(employeesWithCustomers)
        notifyDataSetChanged()
    }
}
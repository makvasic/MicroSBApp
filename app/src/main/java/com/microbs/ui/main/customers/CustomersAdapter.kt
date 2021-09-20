package com.microbs.ui.main.customers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.microbs.R
import com.microbs.databinding.ItemCustomerBinding
import com.microbs.model.Customer

class CustomersAdapter(val onCustomerClick: (customer: Customer) -> Unit) :
    ListAdapter<Customer, CustomersAdapter.CustomerHolder>(object :
        DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.customerId == newItem.customerId
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {
        return CustomerHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
        val customer = getItem(position)
        holder.bind(customer)
        holder.itemView.setOnClickListener {
            onCustomerClick(customer)
        }
    }

    class CustomerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(customer: Customer) {
            val binding = ItemCustomerBinding.bind(itemView)

            binding.customerTextView.text = customer.toString()
        }
    }
}

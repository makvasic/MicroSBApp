package com.microbs.ui.main.customers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.microbs.R
import com.microbs.databinding.FragmentCustomerBinding
import com.microbs.model.Customer
import com.microbs.ui.main.MainViewModel


class CustomerFragment : Fragment() {

    private var _binding: FragmentCustomerBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private var customer = Customer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        arguments?.let {
            customer = it.getParcelable("customer") ?: customer
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.customerNameEditText.setText(customer.name)
        binding.customerPibEditText.setText(customer.pib)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.customer, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemConfirm -> {
                saveCustomer()
                requireActivity().onBackPressed()
                true
            }

            R.id.menuItemDelete -> {
                mainViewModel.deleteCustomer(customer)
                requireActivity().onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun saveCustomer() {

        customer.apply {
            name = binding.customerNameEditText.text.toString()
            pib = binding.customerPibEditText.text.toString()
        }

        mainViewModel.saveCustomer(customer)

    }

    companion object {
        fun newInstance(customer: Customer): CustomerFragment {
            val args = Bundle()
            args.putParcelable("customer", customer)
            val fragment = CustomerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
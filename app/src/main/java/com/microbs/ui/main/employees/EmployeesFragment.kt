package com.microbs.ui.main.employees

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.microbs.databinding.FragmentEmployeesBinding
import com.microbs.ui.Repository
import com.microbs.ui.main.MainViewModel

class EmployeesFragment : Fragment() {

    private var _binding: FragmentEmployeesBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = EmployeesAdapter()
        binding.recyclerView.adapter = adapter

        mainViewModel.employeesLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(it)
            Log.d("TTT", it.size.toString())
        }

        binding.progressBar.visibility = View.VISIBLE
        Log.d("TTT", Repository.userId.toString())
        mainViewModel.getEmployees(Repository.userId)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val TAG = "EmployeesFragment"

        fun newInstance() =
            EmployeesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
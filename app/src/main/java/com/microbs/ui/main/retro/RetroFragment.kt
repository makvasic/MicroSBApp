package com.microbs.ui.main.retro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.microbs.databinding.FragmentRetroBinding

class RetroFragment : Fragment() {

    private var _binding: FragmentRetroBinding? = null
    private val binding get() = _binding!!

    private val retroViewModel: RetroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRetroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = RetrosAdapter()
        binding.recyclerView.adapter = adapter

        retroViewModel.retrosFroUserLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(it)
        }

        binding.progressBar.visibility = View.VISIBLE
        retroViewModel.getRetrosForUser("makvasic")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "RetroFragment"

        fun newInstance() = RetroFragment()
    }
}
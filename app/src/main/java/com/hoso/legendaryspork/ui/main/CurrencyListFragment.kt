package com.hoso.legendaryspork.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hoso.legendaryspork.data.model.CurrencyInfo
import com.hoso.legendaryspork.databinding.CurrencyListFragmentBinding

class CurrencyListFragment : Fragment(), CurrencyListAdapter.CurrencyInfoSelectionCallback {

    companion object {
        fun newInstance() = CurrencyListFragment()
    }

    private lateinit var viewModel: CurrencyViewModel
    private var _binding: CurrencyListFragmentBinding? = null
    private val binding: CurrencyListFragmentBinding get() = _binding!!
    private var adapter: CurrencyListAdapter = CurrencyListAdapter().apply {
        callback = this@CurrencyListFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrencyListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding?.currencyList?.adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        initView()
        initViewListener()
        initObserver()
    }

    private fun initView() {
        binding.currencyList.layoutManager = LinearLayoutManager(requireContext())
        binding.currencyList.adapter = adapter
    }

    fun initViewListener() {
        binding.sort.setOnClickListener {
            viewModel.toggleCurrencyListOrder()
        }
        binding.fetch.setOnClickListener {
            viewModel.fetchCurrencyList()
        }
    }

    fun initObserver() {
        viewModel.currencyList.observe(
            viewLifecycleOwner,
            {
                adapter.submitList(it)
            }
        )
    }

    override fun onSelect(info: CurrencyInfo) {
        Snackbar.make(
            binding.root,
            "Currency ${info.name} selected!",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}

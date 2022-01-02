package com.hoso.legendaryspork.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hoso.legendaryspork.data.model.CurrencyInfo
import com.hoso.legendaryspork.databinding.CurrencyItemBinding

class CurrencyListAdapter : ListAdapter<CurrencyInfo, CurrencyListAdapter.CurrencyViewHolder>(CurrencyInfoCallback()) {
    class CurrencyInfoCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
        override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
            return oldItem == newItem
        }
    }

    interface CurrencyInfoSelectionCallback {
        fun onSelect(info: CurrencyInfo)
    }

    var callback: CurrencyInfoSelectionCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListAdapter.CurrencyViewHolder {
        return CurrencyViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrencyListAdapter.CurrencyViewHolder, position: Int) {
        holder.currencyInfo = getItem(position)
        holder.binding.root.setOnClickListener {
            holder.currencyInfo?.let {
                callback?.onSelect(it)
            }
        }
    }

    inner class CurrencyViewHolder(val binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var currencyInfo: CurrencyInfo? = null
            set(value) {
                field = value
                field?.let {
                    binding.icon.text = it.abbr
                    binding.name.text = it.name
                    binding.symbol.text = it.symbol
                }
            }
    }
}
